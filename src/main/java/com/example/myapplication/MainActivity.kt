package com.example.myapplication

import Topic
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.theme.MyApplicationTheme
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream
import java.nio.charset.StandardCharsets


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Greeting("Android")
                    parseJSON();
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
            text = "Hello $name!",
            modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}

private fun parseJSON() {
    try {
        val inputStream: InputStream = getAssets().open("courses.json")
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, StandardCharsets.UTF_8)
        val jsonObject = JSONObject(json)
        val lecturesArray = jsonObject.getJSONArray("lectures")
        val lectures: MutableList<Lecture> = ArrayList<Lecture>()
        for (i in 0 until lecturesArray.length()) {
            val lectureObj = lecturesArray.getJSONObject(i)
            val lectureID = lectureObj.getInt("lectureID")
            val lectureName = lectureObj.getString("lectureName")
            val lectureIconURL = lectureObj.getString("lectureIconURL")
            val lectureDurationMinutes = lectureObj.getInt("lectureDurationMinutes")
            val topics: MutableList<Int> = ArrayList()
            val topicsArray = lectureObj.getJSONArray("topics")
            for (j in 0 until topicsArray.length()) {
                topics.add(topicsArray.getInt(j))
            }
            val lecture = Lecture(
                lectureID, lectureName, lectureIconURL,
                lectureDurationMinutes, topics
            )
            lectures.add(lecture)
        }
        val topicsArray = jsonObject.getJSONArray("topics")
        val topics: MutableList<Topic> = ArrayList()
        for (i in 0 until topicsArray.length()) {
            val topicObj = topicsArray.getJSONObject(i)
            val topicID = topicObj.getInt("topicID")
            val topicName = topicObj.getString("topicName")
            val topic = Topic(topicID, topicName)
            topics.add(topic)
        }

        // Now you have the lectures and topics data available for further use
        // You can populate the Home screen and handle navigation to the Topics screen
    } catch (e: IOException) {
        e.printStackTrace()
    } catch (e: JSONException) {
        e.printStackTrace()
    }
}
