package den.plugins

import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import kotlinx.serialization.decodeFromString
import den.model.*
import io.ktor.util.*
import kotlinx.serialization.*
import kotlinx.serialization.json.*

private val format = Json { ignoreUnknownKeys = true }

fun Application.configureRouting() {

    routing {

        /*
            This is a sample route to use ignoreKeys with the given json objects
            You can experiment with the given code below.
            Make sure the key of json is the same with the model you'll be creating
        */
        get ("/sample"){
            val jsonStr = """[{"a": "value1", "b": 1, "c": 1}, {"a": "value2", "b": 2, "c": 1}]"""
            //val myList: List<MyData> = JSON.readValue(jsonStr)

            val myList = format.decodeFromString<List<Sample>>(jsonStr)
            val string = format.encodeToString(myList)

            //call.respond(myList.toString())
            call.respond(myList)
        }

        /*
            Let's sample the public api - https://restcountries.com/v3.1/name/philippines
            In this case we are going to use the ignoreKeys since we only need a few data from the datasets
            Let say you only want to get the name in text, make sure to create a model, in this case we have getDataPhilippines
            Make sure the key of json is the same with the model you'll be creating
        */

        get("/data-philippines"){
            val client = HttpClient(CIO).get("https://restcountries.com/v3.1/name/philippines"){
                headers {
                    append(HttpHeaders.ContentType, "application/json")
                    append(HttpHeaders.Accept, "application/json")
                }
            }

            // deserialize into String reference to ClientData model
            // client.body() is the response data of the fetch api
            val stringBody = format.decodeFromString<List<GetDataPhilippines>>(client.body())
            val string = format.encodeToString(stringBody)
            call.respond(stringBody)
        }

        /*
            Let's have a second example the public api - https://random-data-api.com//api/users/random_user
            In this case we are going to use the ignoreKeys since we only need a few data from the datasets
            Let say you only want to get the capital id, uid, and password
            Make sure the key of json is the same with the model you'll be creating
        */

        get("/random-user") {
            val client = HttpClient(CIO).get("https://random-data-api.com//api/users/random_user"){
                headers {
                    append(HttpHeaders.ContentType, "application/json")
                    append(HttpHeaders.Accept, "application/json")
                }
            }

            // deserialize into Object reference to ClientData model
            // client.body() is the response data of the fetch api
            val stringBody = format.decodeFromString<ClientData>(client.body())

            call.respond(stringBody)
        }

    }
}
