package org.listenbrainz.android.ui.screens.yim23

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.listenbrainz.android.model.yimdata.NewReleasesOfTopArtist
import org.listenbrainz.android.model.yimdata.Yim23Screens
import org.listenbrainz.android.ui.components.YimListenCard
import org.listenbrainz.android.util.Utils
import org.listenbrainz.android.viewmodel.Yim23ViewModel

@Composable
fun Yim23NewAlbumsFromTopArtistsScreen (
    viewModel: Yim23ViewModel,
    navController: NavController
) {
    Yim23BaseScreen(
        viewModel     = viewModel,
        navController = navController,
        footerText    = "NEW ALBUMS FROM TOP ARTISTS",
        isUsername    = false,
        downScreen    = Yim23Screens.YimMusicBuddiesScreen
    ) {
        Yim23NewAlbumsFromTopArtists(viewModel = viewModel)
    }
}


@Composable
private fun Yim23NewAlbumsFromTopArtists (viewModel: Yim23ViewModel) {
    val newReleases : List<NewReleasesOfTopArtist> = remember {
        viewModel.getNewReleasesOfTopArtists() ?: listOf()
    }
    Box (modifier = Modifier
        .fillMaxWidth()
        .padding(start = 11.dp, end = 11.dp)
        .clip(
            RoundedCornerShape(10.dp)
        )
        .height(300.dp)
        .background(
            Color(0xFFe0e5de)
        )
    ) {
        LazyColumn (state = rememberLazyListState()) {
            items(newReleases) {
                YimListenCard(releaseName = it.title, artistName = it.artistCreditName, coverArtUrl =
                Utils.getCoverArtUrl(
                    caaId = it.caaId,
                    caaReleaseMbid = it.caaReleaseMbid ,
                    size = 250),)
            }
        }
    }
}