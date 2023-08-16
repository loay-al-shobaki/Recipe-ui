package com.example.recipesui

import android.os.Bundle
import android.provider.MediaStore.Images
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.min
import androidx.compose.ui.unit.sp
import com.example.recipesui.data.Recipe
import com.example.recipesui.data.strawberryCake
import com.example.recipesui.ui.theme.AppBarCollapsedHeight
import com.example.recipesui.ui.theme.AppBarExpendedHeight
import com.example.recipesui.ui.theme.DarkGray
import com.example.recipesui.ui.theme.Gray
import com.example.recipesui.ui.theme.LightGray
import com.example.recipesui.ui.theme.Pink
import com.example.recipesui.ui.theme.RecipesUiTheme
import com.example.recipesui.ui.theme.Spacing
import com.example.recipesui.ui.theme.Transparent
import com.example.recipesui.ui.theme.White
import com.example.recipesui.ui.theme.spacing
import kotlin.math.min

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecipesUiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // MainFragment("Android")
                    val s = MaterialTheme.spacing.extraLarge
                }
            }
        }
    }
}

@Composable
fun MainFragment(recipe: Recipe) {
    val scrollState = rememberLazyListState()
    Box {
        ParallaxToolbar(strawberryCake, scrollState)
        Content(strawberryCake, scrollState)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ParallaxToolbar(recipe: Recipe, scrollState:LazyListState) {
    val imageHeight = AppBarExpendedHeight - AppBarCollapsedHeight

//    val maxOffset =
//        with(LocalDensity.current) { imageHeight.roundToPx() } - LocalWindowInsets.current.systemBars.layoutInsets.top
//
//    val offset = min(scrollState.firstVisibleItemScrollOffset, maxOffset)
//
//    val offsetProgress = max(0.dp, offset * 3f - 2f * maxOffset) / maxOffset

    Scaffold(
        topBar = {
            Column {
                Box(modifier = Modifier.height(imageHeight)) {
                    Image(
                        painter = painterResource(id = R.drawable.strawberry_pie_1),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    colorStops = arrayOf(
                                        Pair(0.3f, Transparent),
                                        Pair(1f, White)
                                    )
                                )
                            )
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Text(
                            text = recipe.category,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier
                                .clip(shape = MaterialTheme.shapes.medium)
                                .background(LightGray)
                                .padding(vertical = 6.dp, horizontal = 16.dp)
                        )

                    }
                }
                Column(
                    Modifier
                        .fillMaxWidth()
                        .height(AppBarCollapsedHeight),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        text = recipe.title,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        },
        content = { it ->
            Box(modifier = Modifier.padding(it))
        }
    )

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .height(AppBarCollapsedHeight)
            .padding(16.dp)
    ) {
        CirclarButton(iconResource = R.drawable.ic_arrow_back)
        CirclarButton(iconResource = R.drawable.ic_favorite)

    }
}

@Composable
fun CirclarButton(
    @DrawableRes iconResource: Int,
    color: Color = Gray,
    elevation: ButtonElevation? = ButtonDefaults.elevatedButtonElevation(),
    onClick: () -> Unit = {}
) {
    Button(
        onClick = { onClick.invoke() },
        contentPadding = PaddingValues(),
        shape = MaterialTheme.shapes.small,
        colors = ButtonDefaults
            .buttonColors(contentColor = color, containerColor = White),
        elevation = elevation,
        modifier = Modifier
            .width(38.dp)
            .height(38.dp)
    ) {
        Icon(painter = painterResource(id = iconResource), contentDescription = null)
    }
}

@Composable
fun Content(recipe: Recipe, scrollState : LazyListState) {
    LazyColumn(contentPadding = PaddingValues(top = AppBarExpendedHeight), state = scrollState) {
        item {
            BasicInfo(recipe)
            Description(recipe)
            ServingCalculator()
            IngredientHeader()
            IngredientesList(recipe)
            ShoppingListButton()
            Reviews(recipe)
            com.example.recipesui.Images()
        }
    }
}

@Composable
fun Images() {
    Row(Modifier.padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween) {
        Image(
            painter = painterResource(id = R.drawable.strawberry_pie_2),
            contentDescription = null,
            modifier = Modifier
                .weight(1f)
                .clip(MaterialTheme.shapes.small)
        )
        Spacer(modifier = Modifier.weight(0.1f))
        Image(
            painter = painterResource(id = R.drawable.strawberry_pie_3),
            contentDescription = null,
            modifier = Modifier
                .weight(1f)
                .clip(MaterialTheme.shapes.small)
        )
    }
}

@Composable
fun Reviews(recipe: Recipe) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(text = stringResource(R.string.reviews), fontWeight = FontWeight.Bold)
            Text(text = recipe.reviews, color = DarkGray)
        }
        Button(
            onClick = { /*TODO*/ }, elevation = null, colors = ButtonDefaults.buttonColors(
                containerColor = Transparent, contentColor = Pink
            )
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "See all")
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_right),
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
fun ShoppingListButton() {
    Button(
        onClick = { /*TODO*/ },
        elevation = null,
        shape = MaterialTheme.shapes.small,
        colors = ButtonDefaults.buttonColors(
            containerColor = LightGray,
            contentColor = Color.Black
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = "Add to shopping list", modifier = Modifier.padding(8.dp))
    }
}

@Composable
fun IngredientesList(recipe: Recipe) {
    EasyGrid(nColumns = 3, itmes = recipe.ingredients) {
        IngredientCard(
            iconResource = it.image, title = it.title,
            subtitle = it.subtitle,
            modifier = Modifier

        )
    }
}

@Composable
fun <T> EasyGrid(nColumns: Int, itmes: List<T>, content: @Composable (T) -> Unit) {
    Column(Modifier.padding(16.dp)) {
        for (i in itmes.indices step nColumns) {
            Row {
                for (j in 0 until nColumns) {
                    if (i + j < itmes.size) {
                        Box(
                            modifier = Modifier
                                .weight(1f),
                            contentAlignment = Alignment.TopCenter
                        ) {
                            content(itmes[i + j])
                        }
                    } else {
                        Spacer(modifier = Modifier.weight(1f, fill = true))
                    }
                }
            }
        }
    }
}

@Composable
fun IngredientCard(
    @DrawableRes iconResource: Int,
    title: String,
    subtitle: String,
    modifier: Modifier.Companion
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(16.dp)
    ) {
        Card(
            shape = MaterialTheme.shapes.large,
            colors = CardDefaults.cardColors(
                containerColor = LightGray
            ),
            modifier = Modifier
                .width(100.dp)
                .height(100.dp)
                .padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = iconResource),
                contentDescription = null,
                modifier = Modifier.padding(16.dp)
            )

        }
        Text(
            text = title,
            modifier = Modifier.width(100.dp),
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
        Text(text = subtitle, color = DarkGray, modifier = Modifier.width(100.dp), fontSize = 14.sp)
    }
}

@Composable
fun IngredientHeader() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .clip(MaterialTheme.shapes.medium)
            .background(LightGray)
            .fillMaxWidth()
            .height(44.dp)
    ) {
        TabButton(text = "Ingredients", activity = true, Modifier.weight(1f))
        TabButton(text = "Tools", activity = false, Modifier.weight(1f))
        TabButton(text = "Steps", activity = false, Modifier.weight(1f))
    }
}

@Composable
fun TabButton(text: String, activity: Boolean, modifier: Modifier) {
    Button(
        onClick = { /*TODO*/ },
        shape = MaterialTheme.shapes.large,
        modifier = modifier.fillMaxHeight(),
        elevation = null,
        colors = if (activity) ButtonDefaults.buttonColors(
            contentColor = White,
            containerColor = Pink
        ) else ButtonDefaults.buttonColors(
            contentColor = DarkGray,
            containerColor = LightGray
        )
    ) {
        Text(text = text)
    }
}

@Composable
fun ServingCalculator() {
    var value by rememberSaveable {
        mutableStateOf(6)
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clip(MaterialTheme.shapes.medium)
            .background(LightGray)
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = stringResource(R.string.serving),
            Modifier.weight(1f),
            fontWeight = FontWeight.Medium
        )
        CirclarButton(iconResource = R.drawable.ic_minus, elevation = null, color = Pink) { value-- }
        Text(text = "$value", Modifier.padding(16.dp), fontWeight = FontWeight.Medium)
        CirclarButton(iconResource = R.drawable.ic_plus, elevation = null,color = Pink) { value++ }
    }
}

@Composable
fun BasicInfo(recipe: Recipe) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        InfoColumn(R.drawable.ic_clock, recipe.cookingTime)
        InfoColumn(R.drawable.ic_flame, recipe.energy)
        InfoColumn(R.drawable.ic_star, recipe.rating)
    }
}

@Composable
fun InfoColumn(@DrawableRes iconResuce: Int, text: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            painter = painterResource(id = iconResuce),
            contentDescription = null,
            tint = Pink,
            modifier = Modifier.height(24.dp),

            )
        Text(text = text, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun Description(recipe: Recipe) {
    Text(
        text = recipe.description,
        fontWeight = FontWeight.Medium,
        modifier = Modifier.padding(16.dp)
    )
}

@Preview(showBackground = true, widthDp = 380, heightDp = 1600)
@Composable
fun GreetingPreview() {
    RecipesUiTheme {
        MainFragment(strawberryCake)
    }
}