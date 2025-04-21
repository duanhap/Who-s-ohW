package com.example.who_s_ohw.ui.feature.home

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollSource.Companion.SideEffect
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.who_s_ohw.R
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay
import okhttp3.internal.http2.Header
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController,viewModel: HomeViewModel = koinViewModel()) {
    Scaffold {
        Surface (
            modifier = Modifier
                .fillMaxSize()
        ){
           HomeContent()
        }
    }
}
@Composable
fun Header(){
    Box(
        modifier = Modifier.fillMaxWidth()
            .height(150.dp)
    ){
        Image(
            painter = painterResource(id = R.drawable.ic_header_bg),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )
        Row (
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(horizontal = 20.dp, vertical = 15.dp)
        ){
            Image(
                painter = painterResource(id = R.drawable.ic_camera),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(90.dp)
            )
            Spacer(modifier = Modifier.width(15.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_alert_red),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
            )
            Spacer(modifier = Modifier.width(5.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_alert_yellow),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
            )
            Spacer(modifier = Modifier.width(5.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_alert_green),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
            )

        }
        SearchBar(
            value = "",
            onValueChange = {},
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(horizontal = 15.dp, vertical = 10.dp)
        )




    }

}
@Composable
fun HomeContent() {
    LazyColumn(
        modifier = Modifier.background(Color(0xFFEEE2BC))
    ){
        item{
            val systemUiController = rememberSystemUiController()
            SideEffect {
                systemUiController.setSystemBarsColor(
                    color = Color(0xFFB79B29),
                    darkIcons = true
                )
            }

            Header()
            val sampleImages = listOf(
                "https://i.postimg.cc/c4YqnG5b/t-i-xu-ng-37.jpg",
                "https://i.postimg.cc/mg2wGQMy/t-i-xu-ng-29.jpg",
                "https://i.postimg.cc/gJfSzndk/t-i-xu-ng-45.jpg",
                "https://i.postimg.cc/JhFKxGsc/t-i-xu-ng-47.jpg",
                "https://i.postimg.cc/gj8Mnmky/t-i-xu-ng-46.jpg"
            )
            ImageCarouselWithAutoScroll(sampleImages)
        }
    }

}
@Composable
fun SearchBar(value: String, onValueChange: (String) -> Unit,modifier: Modifier = Modifier){
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .width(158.dp)
            .height(50.dp)
            .border(1.dp, Color(0xFF6B3E26), RoundedCornerShape(25.dp)),
        shape = RoundedCornerShape(25.dp),
        leadingIcon = {
            Image(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedContainerColor = Color.White.copy(alpha = 0.3f),
            unfocusedContainerColor = Color.White.copy(alpha = 1f),
        ),
        placeholder = {
            Text(
                text = "Search",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Gray.copy(alpha = 0.5f)
            )
        }
    )

}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageCarouselWithAutoScroll(imageUrls: List<String>) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        imageUrls.size // Số lượng trang
    }

//    val coroutineScope = rememberCoroutineScope()
//
//    // Auto scroll
//    LaunchedEffect(pagerState.currentPage) {
//        delay(3000)
//        val nextPage = (pagerState.currentPage + 1) % imageUrls.size
//        pagerState.animateScrollToPage(nextPage)
//    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp)
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .clip(RoundedCornerShape(20.dp))
                .border(1.dp, Color(0xFF6B3E26), RoundedCornerShape(20.dp))
                .shadow(elevation = 4.dp, shape = RoundedCornerShape(20.dp))

        ) { page ->
            Image(
                painter = rememberAsyncImagePainter(model = imageUrls[page]),
                contentDescription = "Image $page",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            repeat(imageUrls.size) { index ->
                val color = if (pagerState.currentPage == index) Color(0xFF6B3E26) else Color(0xFFD8C5A5)
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(color)
                        .border(1.dp, Color(0xFF6B3E26), CircleShape)
                )
            }
        }
    }

}
@Preview(showBackground = true)
@Composable
fun PreviewImageCarousel() {
    val sampleImages = listOf(
        "https://i.postimg.cc/c4YqnG5b/t-i-xu-ng-37.jpg",
        "https://i.postimg.cc/mg2wGQMy/t-i-xu-ng-29.jpg",
        "https://i.postimg.cc/gJfSzndk/t-i-xu-ng-45.jpg",
        "https://i.postimg.cc/JhFKxGsc/t-i-xu-ng-47.jpg",
        "https://i.postimg.cc/gj8Mnmky/t-i-xu-ng-46.jpg"
    )
    ImageCarouselWithAutoScroll(sampleImages)
}
@Preview(showBackground = true)
@Composable
fun PreviewHeader(){
    Header()
}
