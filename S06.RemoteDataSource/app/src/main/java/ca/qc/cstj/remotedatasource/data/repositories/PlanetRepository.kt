package ca.qc.cstj.remotedatasource.data.repositories

import ca.qc.cstj.remotedatasource.core.ApiResult
import ca.qc.cstj.remotedatasource.data.datasources.PlanetDataSource
import ca.qc.cstj.remotedatasource.domain.models.Planet
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class PlanetRepository {

    private val planetDataSource = PlanetDataSource()

    fun retrieveAll(){

    }

    fun retrieveAllWithoutRefresh() : Flow<ApiResult<List<Planet>>> {

        return flow {
            emit(ApiResult.Loading)
            emit(planetDataSource.retrieveAll())

        }
    }
}