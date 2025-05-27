package com.example.who_s_ohw.ui.feature.home

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.nestedscroll.NestedScrollSource.Companion.SideEffect
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.domain.model.Community
import com.example.domain.model.Event
import com.example.domain.model.Feed
import com.example.who_s_ohw.R
import com.example.who_s_ohw.navigation.NotificationScreen
import com.example.who_s_ohw.navigation.ProfileScreen
import com.example.who_s_ohw.navigation.SettingScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay
import okhttp3.internal.http2.Header
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = koinViewModel()) {
    Scaffold {
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            HomeContent(navController)
        }
    }
}

@Composable
fun HeaderAppSearch(onImageClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .background(Color.Transparent)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_header_bg),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )
        Row(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(horizontal = 20.dp, vertical = 15.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_camera),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(90.dp)
                    .clickable {
                        onImageClick()
                    }
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
fun HomeContent(navController: NavController) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color(0xFFB79B29),
            darkIcons = true
        )
    }
    var isOverlayVisible by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize()
                .background(Color(0xFFEEE2BC))
        ) {
            HeaderAppSearch (
                onImageClick = { isOverlayVisible = !isOverlayVisible })
            LazyColumn(
                modifier = Modifier.background(Color(0xFFEEE2BC))
                    .fillMaxWidth()
                    .weight(1f) // chiếm hết phần còn lại
            ) {
                item {

                    val sampleImages = listOf(
                        "https://i.postimg.cc/c4YqnG5b/t-i-xu-ng-37.jpg",
                        "https://i.postimg.cc/mg2wGQMy/t-i-xu-ng-29.jpg",
                        "https://i.postimg.cc/gJfSzndk/t-i-xu-ng-45.jpg",
                        "https://i.postimg.cc/JhFKxGsc/t-i-xu-ng-47.jpg",
                        "https://i.postimg.cc/gj8Mnmky/t-i-xu-ng-46.jpg"
                    )
                    ImageCarouselWithAutoScroll(sampleImages)
                    HomeEventRow(
                        events = listOf(
                            Event(
                                id = 1,
                                title = "Jetpack Compose",
                                name = "Compose Team",
                                eventDateTime = "2025-05-01",
                                description = "Learn how to build beautiful UIs with Compose",
                                createdAt = "111"
                            ),
                            Event(
                                id = 2,
                                title = "Android Clean Architecture",
                                name = "Dev Summit",
                                eventDateTime = "2025-05-15",
                                description = "Deep dive into Clean Architecture principles",
                                createdAt = "112"
                            )
                        ), onClick = {}
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                    HomeCommunityRow(
                        communities = listOf(
                            Community(
                                image = "https://i.postimg.cc/pXdsXwcJ/L-y-FOLLOW-ME.jpg",
                                name = "Family",
                                description = "Learn how to build beautiful UIs with Compose",
                                id = 1,
                                memberCount = 17,
                                isActiveEvent = true,
                                createdByUser = 1,
                                createdAt = "111"
                            ),
                            Community(
                                image = "https://i.postimg.cc/ZRV6GRmt/Premium-Vector-Business-people-standing-together-as-a-team.jpg",
                                name = "Company",
                                description = "Learn how to build beautiful UIs with Compose",
                                id = 2,
                                memberCount = 50,
                                isActiveEvent = true,
                                createdByUser = 1,
                                createdAt = "111"
                            ),
                            Community(
                                image = "https://i.postimg.cc/mDRwxJFM/t-i-xu-ng-36.jpg",
                                name = "Club",
                                description = "Learn how to build beautiful UIs with Compose",
                                id = 3,
                                memberCount = 20,
                                isActiveEvent = true,
                                createdByUser = 1,
                                createdAt = "111"
                            )
                        ), onClick = {}
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                    HomeFeedRow(
                        feeds = listOf(
                            Feed(
                                id = 1,
                                userId = 1,
                                communityId = 1,
                                nameUser = "Harry Potter",
                                nameCommunity = "Company",
                                content = "Chúng ta sẽ có cuộc tập duyệt cưỡi chổi, vì vậy hãy lau chùi thật sạch chiếc xe của mình đi các bạn <3",
                                media = "null",
                                likeCount = 1,
                                commentCount = 1,
                                createdAt = "4 giờ trước",
                                imageUser = "https://i.postimg.cc/x8NMj7GB/Daniel-Radcliffe-s-Harry-Potter-Audition-Is-Just-as-Cute-as-You-Remember.jpg"
                            ),
                            Feed(
                                id = 2,
                                userId = 2,
                                communityId = 2,
                                nameUser = "Hermione Granger",
                                nameCommunity = "Club",
                                content = "Làm xong hết công việc để đầu tháng sau  quẩy banh nóc hot pot nha mn !!",
                                media = "null",
                                likeCount = 123,
                                commentCount = 55,
                                createdAt = "6 giờ trước",
                                imageUser = "https://i.postimg.cc/8PWXtn8y/Hermione-Granger.jpg"
                            ),

                            ), onClick = {}
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                    HomeFeatureRow()
                    Spacer(modifier = Modifier.size(50.dp))

                }
            }
        }
        // Overlay
        OverlayButtons(
            navController = navController,
            isOverlayVisible = isOverlayVisible,
            onCloseOverlay = { isOverlayVisible = false }
        )


    }


}

@Composable
fun OverlayButtons(navController: NavController,isOverlayVisible: Boolean, onCloseOverlay: () -> Unit) {
    if (isOverlayVisible) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f))
                .blur(10.dp)
        )
        Box(
            modifier = Modifier
                .padding(top = 25.dp, start = 35.dp),
        ){
            var isVisible by remember { mutableStateOf(false) }
            LaunchedEffect(isOverlayVisible) {
                isVisible = isOverlayVisible
            }
            AnimatedVisibility(
                visible = isVisible,
                enter = fadeIn() + expandIn(expandFrom = Alignment.Center),
                exit = fadeOut() + shrinkOut(shrinkTowards = Alignment.Center)
            ) {
                Box(
                    modifier = Modifier
                        .width(150.dp)
                        .height(150.dp)
                ) {
                    IconButton(
                        onClick = { navController.navigate(ProfileScreen) },
                        modifier = Modifier
                            .background(Color.White, shape = CircleShape)
                            .size(60.dp)
                            .align(Alignment.TopEnd)

                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Profile",
                            tint = Color(0xFF71361A),
                            modifier = Modifier.size(35.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    IconButton(
                        onClick = { navController.navigate(NotificationScreen) },
                        modifier = Modifier
                            .background(Color.White, shape = CircleShape)
                            .size(60.dp)
                            .align(Alignment.BottomEnd)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "Notification",
                            tint = Color(0xFF71361A),
                            modifier = Modifier.size(35.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    IconButton(
                        onClick = { navController.navigate(SettingScreen)},
                        modifier = Modifier
                            .background(Color.White, shape = CircleShape)
                            .size(60.dp)
                            .align(Alignment.BottomStart)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings",
                            tint = Color(0xFF71361A),
                            modifier = Modifier.size(35.dp)
                        )
                    }
                }
            }

        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top=25.dp, start = 30.dp),
            contentAlignment = Alignment.TopStart
        ) {
            IconButton(onClick = { onCloseOverlay() },
                modifier = Modifier
                    .background(Color.White, shape = CircleShape)
                    .size(71.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close",
                    tint = Color(0xFFBA1A1A),
                    modifier = Modifier.size(48.dp)
                )
            }
        }
    }

}
@Composable
fun SearchBar(value: String, onValueChange: (String) -> Unit, modifier: Modifier = Modifier) {
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
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 2.dp)
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
                val color =
                    if (pagerState.currentPage == index) Color(0xFF6B3E26) else Color(0xFFD8C5A5)
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

@Composable
fun HomeEventRow(events: List<Event>, onClick: (Event) -> Unit) {
    Column {
        Box(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Event",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(
                    Alignment.CenterStart
                ),
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
            Text(
                text = "See all",
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF71361A),
                modifier = Modifier.align(
                    Alignment.CenterEnd
                )
            )
        }
        Spacer(modifier = Modifier.size(8.dp))
        LazyRow(
            modifier = Modifier
        ) {
            item{
                Spacer(modifier = Modifier.size(10.dp))
            }
            items(events,
                key = { it.id }) { event ->
                var isVisible by remember { mutableStateOf(false) }
                LaunchedEffect(Unit) {
                    isVisible = true
                }
                androidx.compose.animation.AnimatedVisibility(
                    visible = isVisible,
                    enter = fadeIn() + expandVertically()
                ) {
                    EventItem(event = event, onClick)
                }
            }
            item{
                Spacer(modifier = Modifier.size(10.dp))
            }
        }
    }
}

@Composable
fun EventItem(event: Event, onClick: (Event) -> Unit) {
    Card(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .size(width = 267.dp, height = 110.dp)
            .clickable { onClick(event) },
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF8E287)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.fillMaxSize()) {
                Spacer(modifier = Modifier.size(7.dp))
                Row(
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = event.title,
                        modifier = Modifier.widthIn(max = 190.dp),
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 18.sp,
                        fontStyle = FontStyle.Italic,
                        color = Color(0xFF71361A)
                    )
                    Text(
                        text = "15 days",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(vertical = 5.dp),
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = Color(0xFF71361A),
                    )
                }
                Spacer(modifier = Modifier.size(5.dp))
                Row(modifier = Modifier.padding(horizontal = 15.dp)) {
                    Text(
                        text = "Name : ",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier,
                        color = Color(0xFF71361A),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = event.name,
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.SemiBold
                        ),
                        modifier = Modifier.padding(horizontal = 2.dp),
                        color = Color(0x63000000),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                }
                Spacer(modifier = Modifier.size(3.dp))
                Row(modifier = Modifier.padding(horizontal = 15.dp)) {
                    Text(
                        text = "Date - time: ",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier,
                        color = Color(0xFF71361A)
                    )
                    Text(
                        text = event.eventDateTime,
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.SemiBold
                        ),
                        modifier = Modifier.padding(horizontal = 2.dp),
                        color = Color(0x63000000),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                }

                Spacer(modifier = Modifier.size(3.dp))
                Row(modifier = Modifier.padding(horizontal = 15.dp)) {
                    Text(
                        text = "Description: ",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier,
                        color = Color(0xFF71361A),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = event.description,
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.SemiBold
                        ),
                        modifier = Modifier
                            .padding(horizontal = 2.dp)
                            .widthIn(max = 100.dp),
                        color = Color(0x63000000),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )


                }
            }
            Image(
                painter = painterResource(id = R.drawable.ic_unbookmark),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(horizontal = 15.dp, vertical = 15.dp)
            )
        }
    }
}

@Composable
fun HomeCommunityRow(communities: List<Community>, onClick: (Community) -> Unit) {
    Column {
        Box(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Community",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(
                    Alignment.CenterStart
                ),
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
            Text(
                text = "See all",
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF71361A),
                modifier = Modifier.align(
                    Alignment.CenterEnd
                )
            )
        }
        Spacer(modifier = Modifier.size(8.dp))
        LazyRow(
            modifier = Modifier
        ) {
            item{
                Spacer(modifier = Modifier.size(10.dp))
            }
            items(communities,
                key = { it.id }) { community ->
                var isVisible by remember { mutableStateOf(false) }
                LaunchedEffect(Unit) {
                    isVisible = true
                }
                androidx.compose.animation.AnimatedVisibility(
                    visible = isVisible,
                    enter = fadeIn() + expandVertically()
                ) {
                    CommunityItem(community = community, onClick)
                }
            }
            item{
                Spacer(modifier = Modifier.size(10.dp))
            }
        }
    }
}

@Composable
fun CommunityItem(community: Community, onClick: (Community) -> Unit) {
    Card(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .size(width = 165.dp, height = 222.dp)
            .clickable { onClick(community) },
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF8E287)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            AsyncImage(
                model = community.image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(width = 165.dp, height = 148.dp)
                    .clip(RoundedCornerShape(15.dp))
            )
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = community.name,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                color = Color(0xFF71361A),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.size(5.dp))
            Text(
                text = "${community.memberCount} members",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.SemiBold

                ),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                color = Color(0xFF43664E),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }

}

@Composable
fun HomeFeedRow(feeds: List<Feed>, onClick: (Feed) -> Unit) {
    Column {
        Box(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Feed",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(
                    Alignment.CenterStart
                ),
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
            Text(
                text = "See all",
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF71361A),
                modifier = Modifier.align(
                    Alignment.CenterEnd
                )
            )
        }
        Spacer(modifier = Modifier.size(8.dp))
        LazyRow(
            modifier = Modifier
        ) {
            item{
                Spacer(modifier = Modifier.size(10.dp))
            }
            items(feeds,
                key = { it.id }) { feed ->
                var isVisible by remember { mutableStateOf(false) }
                LaunchedEffect(Unit) {
                    isVisible = true
                }
                androidx.compose.animation.AnimatedVisibility(
                    visible = isVisible,
                    enter = fadeIn() + expandVertically()
                ) {
                    FeedItem(feed = feed, onClick)
                }
            }
            item{
                Spacer(modifier = Modifier.size(10.dp))
            }
        }
    }
}

@Composable
fun FeedItem(feed: Feed, onClick: (Feed) -> Unit) {
    Card(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .size(width = 273.dp, height = 193.dp)
            .clickable { onClick(feed) },
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF8E287)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(horizontal = 16.dp), // Cho padding 2 bên
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween // đẩy icon 3 chấm ra phải
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        model = feed.imageUser,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = feed.nameUser,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Medium,
                                fontSize = 12.sp
                            ),
                            color = Color(0xFF71361A),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = feed.createdAt,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Light,
                                fontSize = 11.sp
                            ),
                            color = Color(0x63000000),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }

                Image(
                    painter = painterResource(id = R.drawable.ic_option_three_dot),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
            }

            Box(
                modifier = Modifier
                    .size(width = 273.dp, height = 107.dp)
                    .background(Color(0xFFFFEFAD))
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 20.dp, vertical = 5.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = feed.nameCommunity,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = Color(0xFF71361A),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 13.sp
                    )
                    Spacer(modifier = Modifier.size(5.dp))
                    Text(
                        text = feed.content,
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.Light

                        ),
                        color = Color(0xFF71361A),
                        maxLines = 4,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 13.sp
                    )

                }

            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_heart_feed),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = feed.likeCount.toString(),
                        color = Color(0xFF71361A), // màu chữ nâu nâu
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Box(
                    modifier = Modifier
                        .width(1.dp)
                        .height(24.dp)
                        .background(Color(0xFFDECB7D)) // 👈 màu vàng đậm hơn xíu, mờ mờ
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_comment_feed),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = feed.commentCount.toString(),
                        color = Color(0xFF71361A),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

            }

        }



    }

}
@Composable
fun HomeFeatureRow() {
    Column {
        Box(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Features",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(
                    Alignment.CenterStart
                ),
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )

        }
        Spacer(modifier = Modifier.size(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ButtonCircle(icon = painterResource(id = R.drawable.ic_add_note))
            ButtonCircle(icon = painterResource(id = R.drawable.ic_outline_history))
            ButtonCircle(icon = painterResource(id = R.drawable.ic_qr_code))
            ButtonCircle(icon = painterResource(id = R.drawable.ic_connect))
            ButtonCircle(icon = painterResource(id = R.drawable.ic_add_relation))
        }


    }
}
@Composable
fun ButtonCircle(icon: Painter) {
    Surface(
        shape = CircleShape,
        color = Color(0xFFF8E287), // màu vàng vàng
        shadowElevation = 4.dp,
        modifier = Modifier.size(66.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = icon,
                contentDescription = null,
                modifier = Modifier.size(30.dp) // icon hơi to xíu
            )
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
fun PreviewHeader() {
    HeaderAppSearch({})
}

@Preview(showBackground = true)
@Composable
fun PreviewEventItem() {
    val sampleEvent = Event(
        title = "Jetpack Compose",
        name = "Compose Team",
        eventDateTime = "2025-05-01",
        description = "Learn how to build beautiful UIs with Compose",
        id = 1,
        createdAt = "111"
    )
    EventItem(event = sampleEvent, onClick = {})
}

@Preview(showBackground = true)
@Composable
fun PreviewCommunityItem() {
    val sampleCommunity = Community(
        image = "https://i.postimg.cc/pXdsXwcJ/L-y-FOLLOW-ME.jpg",
        name = "Family",
        description = "Learn how to build beautiful UIs with Compose",
        id = 1,
        memberCount = 17,
        isActiveEvent = true,
        createdByUser = 1,
        createdAt = "111"
    )
    CommunityItem(community = sampleCommunity, onClick = {})
}

@Preview(showBackground = true)
@Composable
fun PreviewFeedItem() {
    val sampleFeed = Feed(
        id = 1,
        userId = 1,
        communityId = 1,
        nameUser = "Harry Potter",
        nameCommunity = "Company",
        content = "Chúng ta sẽ có cuộc tập duyệt cưỡi chổi, vì vậy hãy lau chùi thật sạch chiếc xe của mình đi các bạn <3",
        media = "null",
        likeCount = 1,
        commentCount = 1,
        createdAt = "4 giờ trước",
        imageUser = "https://i.postimg.cc/x8NMj7GB/Daniel-Radcliffe-s-Harry-Potter-Audition-Is-Just-as-Cute-as-You-Remember.jpg"
    )
    FeedItem(feed = sampleFeed, onClick = {})
}
//https://i.postimg.cc/x8NMj7GB/Daniel-Radcliffe-s-Harry-Potter-Audition-Is-Just-as-Cute-as-You-Remember.jpg
//https://i.postimg.cc/8PWXtn8y/Hermione-Granger.jpg