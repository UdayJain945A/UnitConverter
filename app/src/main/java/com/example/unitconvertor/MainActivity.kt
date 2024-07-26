package com.example.unitconvertor

import android.content.Intent
import android.health.connect.datatypes.units.Length
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.core.content.ContextCompat.startActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.unitconvertor.ui.theme.UnitConvertorTheme
import kotlin.properties.Delegates


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            App()
        }
    }


    @Composable
    fun App() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "main") {
            composable("main") {
                MainScreen(navController)
            }
            composable("length") {
                LengthScreen()
            }
            composable("weight") {
                weightScreen()
            }
            composable("speed") {
                speedScreen()
            }
        }

    }


    @Composable
    fun MainScreen(navController: NavController) {
        val context = LocalContext.current
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(10.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(text = "ConvertorApp", fontSize = 30.sp, modifier = Modifier.padding(12.dp))
            Image(
                painter = painterResource(id = R.drawable.length1), contentDescription = "Length",
                modifier = Modifier.size(180.dp)
            )
            Button(onClick = {
                navController.navigate("length")

            }, modifier = Modifier.clip(RoundedCornerShape(14.dp)),
                shape = RoundedCornerShape(16.dp)
                )
            {
                Text(text = "Length")
            }

            Image(
                painter = painterResource(id = R.drawable.weight2), contentDescription = "Weight",
                modifier = Modifier
                    .size(180.dp)
                    .padding(12.dp)
            )
            Button(onClick = {
                navController.navigate("weight")

            }, modifier = Modifier,
                shape = RoundedCornerShape(16.dp)
                ) {
                Text(text = "Weight")

            }

            Image(
                painter = painterResource(id = R.drawable.spped), contentDescription = "Speed",
                modifier = Modifier.size(200.dp)
            )
            Button(onClick = {
                navController.navigate("speed")
            }, shape = RoundedCornerShape(16.dp)
                ) {
                Text(text = "Speed")

            }

        }

    }

    @Preview
    @Composable
    fun LengthScreen() {

        var selectedOption1 = rememberSaveable{
            mutableStateOf("")
        }
        var option1 = listOf("Kilometer", "Meter", "Centimeter", "Millimeter")
        var label1="Select Length"
        var text1 =  rememberSaveable{
            mutableStateOf("")

        }

        var selectedOption2 = rememberSaveable{
            mutableStateOf("")
        }

        var option2 = listOf("Kilometer", "Meter", "Centimeter", "Millimeter")
        var label2="Select Length"


        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(50.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Length Converter", fontSize = 30.sp)

            Spacer(modifier = Modifier.size(50.dp))

            dropdownmenu(options = option1 , selectedOption = selectedOption1, label =label1 )

            Spacer(modifier = Modifier.size(50.dp))
            TextField(
                value = text1.value, onValueChange = {newText->
                    text1.value=newText
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                label = { Text(text = "Enter value")}
                
            )
            Spacer(modifier = Modifier.size(50.dp))

            dropdownmenu(options = option2, selectedOption = selectedOption2, label = label2)

            Spacer(modifier = Modifier.size(50.dp))



            var result= rememberSaveable{
                mutableStateOf("Result")

            }
            if(selectedOption1.value!=" " && selectedOption2.value!=" " && text1.value!=""){

                when(selectedOption1.value){
                    "Kilometer"->{
                        var text1=text1.value.toFloat() *1000 //km to meter
                        when(selectedOption2.value){
                            "Kilometer"->{
                                var view1=text1 /1000
                                result.value=view1.toString()
                                Text(text = result.value, modifier = Modifier
                                    .padding(12.dp),
                                    fontSize = 30.sp
                                )

                            }
                            "Meter"->{ var view2=text1 *1
                                result.value=view2.toString()
                                Text(text = result.value,
                                    fontSize = 30.sp
                                )
                            }
                            "Centimeter"->{
                                var view3= text1 * 100
                                result.value=view3.toString()
                                Text(text = result.value
                                ,fontSize = 30.sp)

                            }
                            "Millimeter"->{
                                var view4=text1 *1000
                                result.value=view4.toString()
                                Text(text = result.value,fontSize = 30.sp)
                            }
                        }

                    }
                    "Meter"->{
                        var text2=text1.value.toFloat() * 1 //meter to meter

                        when(selectedOption2.value) {
                            "Kilometer" -> {
                                var view1 = text2 / 1000
                                result.value=view1.toString()
                                Text(text = result.value,fontSize = 30.sp)
                            }

                            "Meter" -> {
                                var view2 = text2 * 1
                                result.value=view2.toString()
                                Text(text = result.value,fontSize = 30.sp)
                            }

                            "Centimeter" -> {
                                var view3 = text2 * 100
                                result.value=view3.toString()
                                Text(text = result.value,fontSize = 30.sp)
                            }

                            "Millimeter" -> {
                                var view4 = text2 * 1000
                                result.value=view4.toString()
                                Text(text = result.value,fontSize = 30.sp)
                            }
                        }



                    }
                    "Centimeter"->{
                        var text3=text1.value.toFloat() * 100       //cm to meter

                        when(selectedOption2.value) {
                            "Kilometer" -> {
                                var view1 = text3 / 1000
                                result.value=view1.toString()
                                Text(text = result.value,fontSize = 30.sp)
                            }

                            "Meter" -> {
                                var view2 = text3 * 1
                                result.value=view2.toString()
                                Text(text = result.value,fontSize = 30.sp)
                            }

                            "Centimeter" -> {
                                var view3 = text3 * 100
                                result.value=view3.toString()
                                Text(text = result.value,fontSize = 30.sp)
                            }

                            "Millimeter" -> {
                                var view4 = text3 * 1000
                                result.value=view4.toString()
                                Text(text = result.value,fontSize = 30.sp)
                            }
                        }

                    }
                    "Millimeter"->{
                        var text4=text1.value.toFloat() / 1000     //mm to meter
                        when(selectedOption2.value) {
                            "Kilometer" -> {
                                var view1 = text4 / 1000
                                result.value=view1.toString()
                                Text(text = result.value,fontSize = 30.sp)
                            }

                            "Meter" -> {
                                var view2 = text4 * 1
                                result.value=view2.toString()
                                Text(text = result.value,fontSize = 30.sp)
                            }

                            "Centimeter" -> {
                                var view3 = text4 * 100
                                result.value=view3.toString()
                                Text(text = result.value,fontSize = 30.sp)
                            }

                            "Millimeter" -> {
                                var view4 = text4 * 1000
                                result.value=view4.toString()
                                Text(text = result.value,fontSize = 30.sp)
                            }
                        }

                    }
                }


            }
        }


        }

    }

    @Preview
    @Composable
    fun speedScreen() {
        var selectedOption1 = rememberSaveable{
            mutableStateOf("")

        }
        var option1 = listOf("Km/h", "m/sec")
        var label1 = "Select Speed"
        var text1 = rememberSaveable{
            mutableStateOf("")

        }

        var selectedOption2 = rememberSaveable{
            mutableStateOf("")
        }
        var option2 = listOf("Km/h", "m/sec")
        var label2 = "Select Speed"


        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(15.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Speed Converter", fontSize = 30.sp)
            Spacer(modifier = Modifier.size(50.dp))
            dropdownmenu(options = option1, selectedOption = selectedOption1, label = label1)
            Spacer(modifier = Modifier.size(50.dp))

            TextField(
                value = text1.value, onValueChange = { newText ->
                    text1.value = newText
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                label = { Text(text = "Enter value") }

            )
            Spacer(modifier = Modifier.size(50.dp))

            dropdownmenu(options = option2, selectedOption = selectedOption2, label = label2)

            Spacer(modifier = Modifier.size(50.dp))


            var result = rememberSaveable{
                mutableStateOf("Result")
            }

            if (selectedOption1.value != " " && selectedOption2.value != " " && text1.value != "") {

                when (selectedOption1.value) {
                    "Km/h" -> {
                        var text1 = (text1.value.toFloat() * 5) / 18 // kh ->m/sec
                        when (selectedOption2.value) {
                            "Km/h" -> {
                                var view1 = (text1 * 18) / 5
                                result.value = view1.toString()
                                Text(
                                    text = result.value, modifier = Modifier
                                        .padding(12.dp)
                                        .size(100.dp),
                                    fontSize = 30.sp
                                )

                            }
                            "m/sec" -> {
                                var view2 = text1 * 1
                                result.value = view2.toString()
                                Text(text = result.value
                                ,fontSize = 30.sp)
                            }
                        }

                    }

                    "m/sec" -> {
                        var text2 = text1.value.toFloat() * 1// m/sec-> m/sec

                                when (selectedOption2.value) {
                                    "Km/h" -> {
                                        var view1 = (text2 * 18) / 5
                                        result.value = view1.toString()
                                        Text(
                                            text = result.value, modifier = Modifier
                                                .padding(12.dp)
                                                .size(100.dp),
                                            fontSize = 30.sp
                                        )

                                    }

                                    "m/sec" -> {
                                        var view2 = text2 * 1
                                        result.value = view2.toString()
                                        Text(text = result.value,fontSize = 30.sp)
                                    }
                                }


                            }

                        }


                    }
                }

            }


    @Preview
    @Composable
    fun weightScreen() {
        var selectedOption1 = rememberSaveable{
            mutableStateOf("")
        }

        var option1 = listOf("Kilogram", "gram", "Milligram", "Microgram")
        var label1="Select Weight"
        var text1= rememberSaveable{
            mutableStateOf("")
        }

        var selectedOption2 = rememberSaveable{
            mutableStateOf("")
        }
        var option2 = listOf("Kilogram", "gram", "Milligram", "Microgram")
        var label2="Select Weight"



        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(50.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(text = "Weight Converter", fontSize = 30.sp)
            Spacer(modifier = Modifier.size(50.dp))
            dropdownmenu(options = option1, selectedOption = selectedOption1, label = label1)
            Spacer(modifier = Modifier.size(50.dp))

            TextField(
                value = text1.value, onValueChange = {newText->
                    text1.value=newText
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                label = { Text(text = "Enter value")}

            )
            Spacer(modifier = Modifier.size(50.dp))


            dropdownmenu(options = option2, selectedOption = selectedOption2 , label = label2 )
            Spacer(modifier = Modifier.size(50.dp))


            var result= rememberSaveable{
                mutableStateOf("Result")
            }
            if(selectedOption1.value!=" " && selectedOption2.value!=" " && text1.value!=""){

                when(selectedOption1.value){
                    "Kilogram"->{
                        var text1=text1.value.toFloat() *1000 //km to gram
                        when(selectedOption2.value){
                            "Kilogram"->{
                                var view1=text1 /1000
                                result.value=view1.toString()
                                Text(text = result.value, modifier = Modifier
                                    ,fontSize = 30.sp
                                )

                            }
                            "gram"->{ var view2=text1 *1
                                result.value=view2.toString()
                                Text(text = result.value,fontSize = 30.sp)
                            }
                            "Milligram"->{
                                var view3= text1 * 1000
                                result.value=view3.toString()
                                Text(text = result.value,fontSize = 30.sp)

                            }
                            "Microgram"->{
                                var view4=text1 *1000000
                                result.value=view4.toString()
                                Text(text = result.value,fontSize = 30.sp)
                            }
                        }

                    }
                    "gram"->{
                        var text2=text1.value.toFloat() * 1 // gram to gram

                        when(selectedOption2.value){
                            "Kilogram"->{
                                var view1=text2/ 1000
                                result.value=view1.toString()
                                Text(text = result.value, modifier = Modifier
                                    .padding(12.dp)
                                    .size(100.dp),fontSize = 30.sp
                                )

                            }
                            "gram"->{ var view2=text2 *1
                                result.value=view2.toString()
                                Text(text = result.value,fontSize = 30.sp)
                            }
                            "Milligram"->{
                                var view3= text2 * 1000
                                result.value=view3.toString()
                                Text(text = result.value,fontSize = 30.sp)

                            }
                            "Microgram"->{
                                var view4=text2 *1000000
                                result.value=view4.toString()
                                Text(text = result.value,fontSize = 30.sp)
                            }
                        }

                    }
                    "Milligram"->{
                        var text3=text1.value.toFloat() / 1000      //mg to g

                        when(selectedOption2.value) {
                            "Kilogram" -> {
                                var view1 = text3 / 1000
                                result.value = view1.toString()
                                Text(
                                    text = result.value, modifier = Modifier
                                        .padding(12.dp)
                                        .size(100.dp),fontSize = 30.sp
                                )

                            }

                            "gram" -> {
                                var view2 = text3 * 1
                                result.value = view2.toString()
                                Text(text = result.value,fontSize = 30.sp)
                            }

                            "Milligram" -> {
                                var view3 = text3 * 1000
                                result.value = view3.toString()
                                Text(text = result.value,fontSize = 30.sp)

                            }

                            "Microgram" -> {
                                var view4 = text3 * 1000000
                                result.value = view4.toString()
                                Text(text = result.value,fontSize = 30.sp)
                            }

                        }

                    }
                    "Microgram"->{
                        var text4=text1.value.toFloat() / 1000000     //mg to gram
                        when(selectedOption2.value){
                            "Kilogram"->{
                                var view1=text4/ 1000
                                result.value=view1.toString()
                                Text(text = result.value, modifier = Modifier
                                    .padding(12.dp)
                                    .size(100.dp),fontSize = 30.sp
                                )

                            }
                            "gram"->{ var view2=text4 *1
                                result.value=view2.toString()
                                Text(text = result.value,fontSize = 30.sp)
                            }
                            "Milligram"->{
                                var view3= text4 * 1000
                                result.value=view3.toString()
                                Text(text = result.value,fontSize = 30.sp)

                            }
                            "Microgram"->{
                                var view4=text4 *1000000
                                result.value=view4.toString()
                                Text(text = result.value,fontSize = 30.sp)
                            }
                        }
                        }

                    }
                }


            }
        }






    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun dropdownmenu(
        options:List<String>,
        selectedOption:MutableState<String>,
        label:String,
    ){
        var expanded = rememberSaveable{
            mutableStateOf(false)
        }

        ExposedDropdownMenuBox(
            expanded = expanded.value,
            onExpandedChange ={ expanded.value= !expanded.value} )
        {

            TextField(
                readOnly = true,
                value = selectedOption.value,
                onValueChange = {},
                label ={ Text(text = label)},
                trailingIcon =
                {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded.value)
                },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(0.90f)
            )

            ExposedDropdownMenu(expanded = expanded.value, onDismissRequest = {expanded.value=false }) {

                options.forEach { option->
                    DropdownMenuItem(text = { Text(text = option)},
                        onClick = {
                            selectedOption.value=option
                            expanded.value=false
                        })
                }
            }
        }
    }


























