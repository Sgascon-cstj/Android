package ca.qc.cstj.remotedatasource.data.datasources

import android.util.Log
import ca.qc.cstj.remotedatasource.core.ApiResult
import ca.qc.cstj.remotedatasource.core.Constants
import ca.qc.cstj.remotedatasource.domain.models.Planet
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result
import org.json.JSONObject

class PlanetDataSource {
    fun retrieveAll() : ApiResult<List<Planet>>{
        //Fait un get http sure l'url contenue dans la constante
        val (_,_,result) = Constants.BasURL.PLANETS.httpGet().responseJson()
        return when(result){
            is Result.Success ->{
                val jsonPlanets = result.value.array()//La reponse du serveur en jhson
                val planets = mutableListOf<Planet>()//La liste d'objet de mon modèle
                for (i in 0.. jsonPlanets.length()){//La boucle pour transfomer le json dans le format qu'on veut
                    planets.add(deserlializePlanet(jsonPlanets.getJSONObject(i)))
                }
                ApiResult.Success(planets)
            }
            is Result.Failure -> ApiResult.Error(result.error.exception)
        }
    }
    private fun deserlializePlanet(planetJson: JSONObject) : Planet{
        val name = planetJson.getString("name")
        val temperature = planetJson.getDouble("temperature")
        val image = planetJson.getString("icon")

        return Planet(name,image,temperature)
    }

}