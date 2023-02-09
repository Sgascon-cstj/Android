package com.example.mvvm.presentation.main

import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.DecelerateInterpolator
import android.view.animation.LinearInterpolator
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.lifecycleScope
import com.example.mvvm.R
import com.example.mvvm.core.Constants
import com.example.mvvm.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private  val viewModel : MainViewModel by viewModels()

    private  lateinit var rocketAnimation: ValueAnimator
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Inflate crée les objets des contrôles (btn, imageView, textView, ...)
        //Attention il faut changer le pramètre de l'appel de setContentView!
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //À chaque fois qu'un nouveau state est recu
        viewModel.mainUiState.onEach {
            when(it){
                MainUiState.Empty -> Unit
                is MainUiState.Error -> {
                    Snackbar.make(binding.root,R.string.msgRecharge, Snackbar.LENGTH_INDEFINITE)
                        .setAction(R.string.recharge){
                            viewModel.recharge()
                        }.show()
                }
                MainUiState.Loading -> {
                    binding.btnStart.isEnabled = false
                    rocketAnimation.start()
                }
                is MainUiState.Success -> {
                    with(binding){

                        txvPiloteName.text = it.pilot.name
                        txvLife.text = it.pilot.life.toString()
                        txvLevel.text = getString(R.string.level, it.pilot.level)
                        txvenergy.text = it.pilot.energy.toString()
                        txvCube.text = it.pilot.cube.toString()
                        txvShield.text = it.pilot.shield.toString()
                    }
                    binding.btnStart.isEnabled = true

                }
            }

        }.launchIn(lifecycleScope)//Si il n'y as pas cette ligne le changement d'état n'arrivera jamais
       binding.btnStart.setOnClickListener {
           val revolution = binding.sldRevolution.value.toInt()
           val isTraining = binding.swtTrainig.isChecked

           createAnimation()
           viewModel.fly(revolution,isTraining)
       }
    }

    private fun createAnimation(){
        val layoutParams = binding.imvRocket.layoutParams as ConstraintLayout.LayoutParams
        val repeatCount = binding.sldRevolution.value.toInt()
        val startAngle = layoutParams.circleAngle
        val endAngle = startAngle - 360

        rocketAnimation = ValueAnimator.ofFloat(startAngle, endAngle)
        rocketAnimation.repeatCount = repeatCount - 1
        rocketAnimation.duration = Constants.REVOLUTION_DURATION
        rocketAnimation.interpolator = DecelerateInterpolator()
        rocketAnimation.addUpdateListener {
            val animatedValue = it.animatedValue as Float
            layoutParams.circleAngle = animatedValue
            binding.imvRocket.layoutParams = layoutParams
            binding.imvRocket.rotation = animatedValue - 90


        }
    }
}