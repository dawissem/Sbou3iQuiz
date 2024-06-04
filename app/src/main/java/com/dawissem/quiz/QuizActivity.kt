package com.dawissem.quiz

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.dawissem.quiz.databinding.ActivityQuizBinding
import com.dawissem.quiz.databinding.QuizItemReyclerRowBinding
import com.dawissem.quiz.databinding.ScoreDialogBinding
import com.dawissem.quiz.QuizModel
import com.google.android.material.color.utilities.Score

class QuizActivity : AppCompatActivity(), View.OnClickListener {


    companion object {

        var questionModelList: List<QuizModel.QuestionModel> = listOf()
        var time: String = ""

    }

    lateinit var binding: ActivityQuizBinding

    var currentQuestionsIndex = 0;
    var selectedAnswer = ""
    var score = 0 ;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            btn0.setOnClickListener(this@QuizActivity)
            btn1.setOnClickListener(this@QuizActivity)
            btn2.setOnClickListener(this@QuizActivity)
            nextBtn.setOnClickListener(this@QuizActivity)

        }
        loadQuestions()
        startTimer()
    }

    private fun startTimer() {
        val totalTimeInMillis = time.toInt() * 60 * 1000L
        object : CountDownTimer(totalTimeInMillis, 1000L) {


            override fun onTick(millisUntilFinished: Long) {
                val seconds = millisUntilFinished / 1000
                val minutes = seconds / 60
                val remainingSeconds = seconds % 60
                binding.timerIndicatorTextview.text =
                    String.format("%02d:%02d", minutes, remainingSeconds)
            }

            override fun onFinish() {
                //finsish the quizzz
            }

        }.start()

    }


    private fun loadQuestions() {
        selectedAnswer=""

        if (currentQuestionsIndex == questionModelList.size){
            finishQuiz()
            return
            }

        binding.apply {
            questionIndicatorTextview.text = "Question ${currentQuestionsIndex+1}/ ${questionModelList.size} "
            questionProgressIndicator.progress =
                (currentQuestionsIndex.toFloat() / questionModelList.size.toFloat() * 100).toInt()
            questionTextview.text = questionModelList[currentQuestionsIndex].question
            btn0.text = questionModelList[currentQuestionsIndex].options[0]
            btn1.text = questionModelList[currentQuestionsIndex].options[1]
            btn2.text = questionModelList[currentQuestionsIndex].options[2]
        }
    }

    override fun onClick(view: View?) {

        binding.apply {
            btn0.setBackgroundColor(getColor(R.color.gray))
            btn1.setBackgroundColor(getColor(R.color.gray))
            btn2.setBackgroundColor(getColor(R.color.gray))

        }

        val clickedBtn = view as Button
        if (clickedBtn.id == R.id.next_btn) //bouton suivant est clique
        { if
           (selectedAnswer== questionModelList[currentQuestionsIndex].correct)
        {
            score++
            Log.i("Score of quiz  ", score.toString())
        }
            currentQuestionsIndex++
            loadQuestions()
        }
        else
        {
            selectedAnswer = clickedBtn.text.toString()
            clickedBtn.setBackgroundColor(getColor(R.color.blue))
        }


    }
    @SuppressLint("SetTextI18n")
    private fun finishQuiz(){
        val totalQuestions = questionModelList.size
        val percentage= ((score.toFloat() / totalQuestions.toFloat() ) *100 ).toInt()

        val dialogBinding = ScoreDialogBinding.inflate(layoutInflater)
        dialogBinding.apply {
            scoreProgressIndicator.progress = percentage
            scoreProgressText.text = "$percentage % "

            if (percentage>60)
            {
                scoreTitle.text="mabrouk! 3adit les tests "
                scoreTitle.setTextColor(Color.BLUE)
            }
            else
            {
                scoreTitle.text="samhni sidi khouya 8lat "
                scoreTitle.setTextColor(Color.RED)
            }

            scoreDescription.text ="$score  min $totalQuestions s7a7"
            finishBtn.setOnClickListener{
                finish()
            }
        }

        ///
        AlertDialog.Builder(this)
            .setView(dialogBinding.root)
            .setCancelable(false)
            .show()
    }
}