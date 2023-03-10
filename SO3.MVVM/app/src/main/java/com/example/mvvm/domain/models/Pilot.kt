package com.example.mvvm.domain.models

import com.example.mvvm.core.Constants
import kotlin.random.Random

data class Pilot(var name: String, var life: Int, var cube: Int = 0) {

    var shield: Int = 10
    var energy: Int = 15

    private var _experience: Int = 0
    val level: Int get() { // get() = _experience / 10
        return _experience / Constants.EXPERIENCE_PER_LEVEL
    }

    fun fly(revolutions: Int, isTraining: Boolean){
        if (!isTraining)
        {
            _experience += revolutions * Random.nextInt(1, 2 * level + 2)
            shield -= Random.nextInt(0,2)
            life -= Random.nextInt(0,2)
            cube += Random.nextInt(0,2 * level + 1)
        }
        energy -= Random.nextInt(1,3)
    }
    fun canFly(): Boolean{
        return  life > 0 && energy > 0
    }

    fun recharge(){
        //TODO: Add Microtransaction
        energy = Random.nextInt(0,10)
        life = 10
    }
}