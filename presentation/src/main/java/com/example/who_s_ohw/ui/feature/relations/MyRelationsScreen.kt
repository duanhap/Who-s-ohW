package com.example.who_s_ohw.ui.feature.relations

import android.annotation.SuppressLint
import android.widget.ImageButton
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.domain.model.Community
import com.example.domain.model.Relations.Relation
import com.example.domain.model.Relations.RelationGroup
import com.example.domain.model.Relations.RelationType
import com.example.domain.model.User
import com.example.who_s_ohw.R
import com.example.who_s_ohw.ui.feature.auth.SignOutContentField
import com.example.who_s_ohw.ui.feature.auth.SignUpContent
import com.example.who_s_ohw.ui.feature.home.HeaderAppSearch
import com.example.who_s_ohw.ui.feature.home.OverlayButtons
import com.example.who_s_ohw.ui.feature.profile.HeaderAppNormalBack
import java.time.format.TextStyle

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyRelationsScreen(navController: NavController) {
    Scaffold (floatingActionButton = {
        FloatingActionButton(
            onClick = { /* xử lý sự kiện */ },
            containerColor = Color(0xFF43664E), // màu nền
            contentColor = Color.White,         // màu icon
            shape = CircleShape
        ) {
            Icon(
                Icons.Default.Add, contentDescription = "Thêm"
            )
        }
    },
        floatingActionButtonPosition = FabPosition.End, ){
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {

            MyRelationsContent(navController)
        }
    }
}
@Composable
fun MyRelationsContent(navController: NavController) {
    val user = remember {
        User(
            id = 1,
            name = "Severus Snape",
            email = "",
            password = "1234567",
            avatar = "https://i.postimg.cc/Gp1Rm5Cz/Which-Bad-Harry-Potter-Witch-Are-You.jpg",
            biography = "Always.",
            createdAt = "2025-05-27 17:14:43",
            isFake = false,
            mobile = "0347508543",
            dateOfBirth = "09/01/1990"
        ) }
    var isOverlayVisible by remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize()
                .background(Color(0xFFEEE2BC))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(348.dp)
                    .shadow(
                        elevation = 8.dp,
                        shape = RoundedCornerShape(
                            bottomStart = 10.dp,
                            bottomEnd = 10.dp
                        )
                    )
                    .background(
                        color = Color(0xFFF8E287),
                        shape = RoundedCornerShape(
                            bottomStart = 10.dp,
                            bottomEnd = 10.dp
                        )
                    )
            ){

                HeaderAppSearch (onImageClick = { isOverlayVisible = !isOverlayVisible })
                BoxUserInformation(modifier = Modifier.align(Alignment.BottomCenter).offset(y=(10).dp),user)
            }
            RelationGroupColumn(
                relationGroups =  listOf(
                    RelationGroup(
                        id = 1,
                        name = "My family",
                        userID = 1,
                        isActiveEvent = false,
                        relationTypes = listOf(
                            RelationType(
                                id = 1,
                                relationId = 1,
                                relationName = "Dad",
                                relationGroupId = 1,
                                user = User(
                                    id = 3,
                                    name = "Tobias Snape",
                                    email = "albusdumbledore@gmail.com",
                                    password = "1234567",
                                    avatar = "",
                                    biography = "Happiness can be found even in the darkest of times, if one only remembers to turn on the light.",
                                    createdAt = "2025-05-27 17:14:43",
                                    isFake = false,
                                    mobile = "0347508638",
                                    dateOfBirth = "01/01/1981"
                                )
                            ),
                            RelationType(
                                id = 2,
                                relationId = 2,
                                relationName = "Mom",
                                relationGroupId = 1,
                                user = User(
                                    id = 3,
                                    name = "Eileen Prince",
                                    email = "albusdumbledore@gmail.com",
                                    password = "1234567",
                                    avatar = "",
                                    biography = "Happiness can be found even in the darkest of times, if one only remembers to turn on the light.",
                                    createdAt = "2025-05-27 17:14:43",
                                    isFake = false,
                                    mobile = "0347508638",
                                    dateOfBirth = "01/01/1981"
                                )
                            )
                        )
                    ),
                    RelationGroup(
                        id = 2,
                        name = "Other people",
                        userID = 1,
                        isActiveEvent = false,
                        relationTypes = listOf(
                            RelationType(
                                id = 3,
                                relationId = 3,
                                relationName = "Teacher",
                                relationGroupId = 1,
                                user = User(
                                    id = 2,
                                    name = "Albus Dumbledore",
                                    email = "albusdumbledore@gmail.com",
                                    password = "1234567",
                                    avatar = "https://i.postimg.cc/HkdXD84V/Albus-Dubeldor.jpg",
                                    biography = "Happiness can be found even in the darkest of times, if one only remembers to turn on the light.",
                                    createdAt = "2025-05-27 17:14:43",
                                    isFake = false,
                                    mobile = "0347508638",
                                    dateOfBirth = "01/01/1981"
                                )
                            )
                        )
                    )
                ),onClick = {}
            )

            // Spacer(modifier = Modifier.size(10.dp))
            //val scrollState = rememberScrollState()




        }
        OverlayButtons(
            navController = navController,
            isOverlayVisible = isOverlayVisible,
            onCloseOverlay = { isOverlayVisible = false }
        )
    }


}
@SuppressLint("DefaultLocale")
@Composable
fun BoxUserInformation(modifier: Modifier,user: User){
    Box(
        modifier = modifier
            .padding(horizontal = 15.dp)
            .fillMaxWidth()
    ){
        Row {
            Column (horizontalAlignment = Alignment.CenterHorizontally){
                AsyncImage(
                    model = user.avatar,
                    contentDescription = "Ảnh từ URL",
                    placeholder = painterResource(id = R.drawable.ic_profile),
                    error = painterResource(id = R.drawable.ic_profile),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(137.dp)
                        .height(148.dp)
                        .clip(RoundedCornerShape(5.dp)) // ← thêm dòng này để ảnh bo góc
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row{
                    Text(text = "ID: ", fontSize = 12.sp, color = Color(0xFF71361A), style = MaterialTheme.typography.titleMedium )
                    Text(text = buildString {
                        append("#")
                        append(String.format("%06d", user.id))
                    }, fontSize = 12.sp, color = Color(0x63000000), style = MaterialTheme.typography.bodyLarge )

                }

            }
            Spacer(modifier = Modifier.width(35.dp))
            Column ( verticalArrangement = Arrangement.spacedBy(4.dp), modifier = Modifier.offset(y = (-6).dp)){
                Text(text = "Name: ${user.name}", fontSize = 15.sp, color = Color(0xFF71361A), style = MaterialTheme.typography.titleMedium,fontWeight = FontWeight.Bold,fontStyle = FontStyle.Italic)
                Row{
                    Text(text = "Date of birth: ", fontSize = 12.sp, color = Color(0xFF71361A), style = MaterialTheme.typography.titleMedium )
                    Text(text =user.dateOfBirth, fontSize = 12.sp, color = Color(0x63000000), style = MaterialTheme.typography.bodyLarge )

                }
                Row{
                    Text(text = "Mobile: ", fontSize = 12.sp, color = Color(0xFF71361A), style = MaterialTheme.typography.titleMedium )
                    Text(text =user.mobile, fontSize = 12.sp, color = Color(0x63000000), style = MaterialTheme.typography.bodyLarge )
                }
                Row{
                    Text(text = "About me: ", fontSize = 12.sp, color = Color(0xFF71361A), style = MaterialTheme.typography.titleMedium )
                }
                BasicTextField(
                    value = user.biography,
                    onValueChange = {},
                    enabled = false, // ← không cho nhập
                    readOnly = true, // ← chỉ để hiển thị
                    modifier = Modifier
                        .width(171.dp)
                        .height(66.dp)
                        .border(1.dp, Color(0x8071361A), RoundedCornerShape(3.dp))
                        .background(Color(0xFFFFFFFF))
                        .padding(8.dp),
                    textStyle = androidx.compose.ui.text.TextStyle(
                        fontSize = 12.sp,
                        color = Color.Black
                    )
                )
                Row (modifier = Modifier.align(Alignment.End)
                    .offset(y =(-10).dp,x = (35).dp)){
                    IconButton(onClick = { /* hành động khi nhấn */ }) {
                        Icon(
                            modifier = Modifier.size(23.dp),
                            painter = painterResource(id = R.drawable.ic_connect),
                            contentDescription = "Mô tả",
                            tint = Color.Unspecified // Giữ nguyên màu ảnh gốc nếu là ảnh bitmap
                        )
                    }
                    IconButton(onClick = { /* hành động khi nhấn */ }) {
                        Icon(
                            modifier = Modifier.size(22.dp),
                            painter = painterResource(id = R.drawable.ic_edit),
                            contentDescription = "Mô tả",
                            tint = Color.Unspecified // Giữ nguyên màu ảnh gốc nếu là ảnh bitmap
                        )
                    }
                }



            }
        }

    }
}
@Composable
fun RelationGroupColumn(relationGroups: List<RelationGroup>, onClick: (RelationType) -> Unit) {
    LazyColumn(
        modifier = Modifier
    ) {
        item{
            Spacer(modifier = Modifier.size(10.dp))
        }
        items(relationGroups,
            key = { it.id }) { RelationGroup ->
//            var isVisible by remember { mutableStateOf(false) }
//            LaunchedEffect(Unit) {
//                isVisible = true
//            }
//            androidx.compose.animation.AnimatedVisibility(
//                visible = isVisible,
//                enter = fadeIn() + expandVertically()
//            ) {
//                RelationTypeRow (relationGroup = RelationGroup,relationTypes = RelationGroup.relationTypes ,onClick = onClick)
//            }
            RelationTypeRow (relationGroup = RelationGroup,relationTypes = RelationGroup.relationTypes ,onClick = onClick)

        }
        item{
            Spacer(modifier = Modifier.size(10.dp))
        }
    }
}
@Composable
fun RelationTypeRow(relationGroup: RelationGroup, relationTypes: List<RelationType>, onClick: (RelationType) -> Unit) {
    Column {
        Box(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
        ) {
            Row {
                Text(
                    text = relationGroup.name,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 20.sp
                    ),
                    modifier = Modifier.align(Alignment.CenterVertically),
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                )
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .wrapContentSize()
                        .background(color = Color(0xFFF8E287), shape = RoundedCornerShape(30.dp))
                        .padding(horizontal = 4.dp, vertical = 2.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.wrapContentSize()
                    ) {
                        IconButton(
                            onClick = { /* hành động */ },
                            modifier = Modifier.size(32.dp) // Giảm size nút
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_visibility),
                                contentDescription = "Xem",
                                modifier = Modifier.size(16.dp),
                                tint = Color.Unspecified
                            )
                        }
                        IconButton(
                            onClick = { /* hành động */ },
                            modifier = Modifier.size(32.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_setting),
                                contentDescription = "Cài đặt",
                                modifier = Modifier.size(16.dp),
                                tint = Color.Unspecified
                            )
                        }
                    }
                }



            }

        }
        Spacer(modifier = Modifier.size(8.dp))
        LazyRow(
            modifier = Modifier
        ) {
            item{
                Spacer(modifier = Modifier.size(10.dp))
            }
            items(relationTypes,
                key = { it.id }) { relationType ->
                var isVisible by remember { mutableStateOf(false) }
                LaunchedEffect(Unit) {
                    isVisible = true
                }
                androidx.compose.animation.AnimatedVisibility(
                    visible = isVisible,
                    enter = fadeIn() + expandVertically()
                ) {
                    RelationTypeItem(relationType = relationType, onClick)
                }
            }
            item{
                Spacer(modifier = Modifier.size(10.dp))
            }
        }
    }
}

@Composable
fun RelationTypeItem(relationType: RelationType, onClick: (RelationType) -> Unit) {
    Card(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .size(width = 137.dp, height = 172.dp)
            .clickable { onClick(relationType) },
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF8E287)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            AsyncImage(
                model = relationType.user.avatar,
                contentDescription = null,
                placeholder = painterResource(id = R.drawable.ic_profile),
                error = painterResource(id = R.drawable.ic_profile),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(width = 137.dp, height = 121.dp)
                    .clip(RoundedCornerShape(15.dp))
            )
            Text(
                text = relationType.relationName,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                color = Color(0xFF71361A),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 15.sp
            )
            Text(
                text = relationType.user.name,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Light

                ),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                color = Color(0xFF43664E),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 15.sp

            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewMyRelationsScreen() {
    val navController = rememberNavController()
    MyRelationsScreen(navController)
}
@Preview(showBackground = true)
@Composable
fun PreviewBoxUserInformation() {
    val user = remember {
        User(
            id = 1,
            name = "Severus Snape",
            email = "",
            password = "1234567",
            avatar = "https://i.postimg.cc/Gp1Rm5Cz/Which-Bad-Harry-Potter-Witch-Are-You.jpg",
            biography = "Always.",
            createdAt = "2025-05-27 17:14:43",
            isFake = false,
            mobile = "0347508543",
            dateOfBirth = "09/01/1990"
        ) }
    BoxUserInformation(modifier = Modifier,user)
}
@Preview(showBackground = true)
@Composable
fun PreviewRelationTypeRow() {
    val relationGroup = remember {
        RelationGroup(
            id = 1,
            name = "Other people",
            userID = 1,
            isActiveEvent = false,
            listOf()
        ) }
    val listRelationType = remember {
        listOf(
            RelationType(
                id = 1,
                relationId = 1,
                relationName = "Teacher",
                relationGroupId = 1,
                user = User(
                    id = 1,
                    name = "Severus Snape",
                    email = "",
                    password = "1234567",
                    avatar = "https://i.postimg.cc/Gp1Rm5Cz/Which-Bad-Harry-Potter-Witch-Are-You.jpg",
                    biography = "Always.",
                    createdAt = "2025-05-27 17:14:43",
                    isFake = false,
                    mobile = "0347508543",
                    dateOfBirth = "09/01/1990"
                )
            )
        )
    }

    RelationTypeRow(relationGroup = relationGroup, relationTypes = listRelationType, onClick = {})
}
@Preview(showBackground = true)
@Composable
fun PreviewRelationTypeItem() {
    val relationType = remember {
        RelationType(
            id = 1,
            relationId = 1,
            relationName = "Teacher",
            relationGroupId = 1,
            user = User(
                id = 2,
                name = "Albus Dumbledore",
                email = "albusdumbledore@gmail.com",
                password = "1234567",
                avatar = "https://i.postimg.cc/HkdXD84V/Albus-Dubeldor.jpg",
                biography = "Happiness can be found even in the darkest of times, if one only remembers to turn on the light.",
                createdAt = "2025-05-27 17:14:43",
                isFake = false,
                mobile = "0347508638",
                dateOfBirth = "01/01/1981"
            )
        )

    }
    RelationTypeItem(relationType =relationType, onClick = {})
}


