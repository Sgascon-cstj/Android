package ca.qc.cstj.remotedatasource.core

import kotlinx.serialization.json.Json
//Class pour la serialization faire hériter les dataSources de cette class
abstract class JsonDataSource {
    private val _json = Json { ignoreUnknownKeys = true }
    protected val json get() = _json

}