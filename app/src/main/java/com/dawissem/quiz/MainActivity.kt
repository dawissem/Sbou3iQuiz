package com.dawissem.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.dawissem.quiz.databinding.ActivityMainBinding
import com.dawissem.quiz.QuizModel

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    lateinit var quizModelList:MutableList<QuizModel>
    lateinit var adapter: QuizListAdapter
    override fun onCreate(savedInstanceState: Bundle?)
    {

        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        quizModelList= mutableListOf()
        getDataFromFirebase()

    }

    private fun setupRecyclerView(){
        adapter= QuizListAdapter(quizModelList )
        binding.recyclerView.layoutManager= LinearLayoutManager(this)
        binding.recyclerView.adapter=adapter

    }

    private fun getDataFromFirebase(){


        val listQuestionModel = mutableListOf<QuizModel.QuestionModel>()
        listQuestionModel.add(
            QuizModel.QuestionModel(
                " اش كان يخدم السبوعي ؟",
                mutableListOf("عساس", "طبيب","فرملي "),
                correct = "فرملي") )

        listQuestionModel.add(
            QuizModel.QuestionModel(
                "شكون يحي السبوعي  ؟",
                mutableListOf("امو", "عزه", "خوه"),
                correct = "امو") )


        listQuestionModel.add(
            QuizModel.QuestionModel(
                "شنيا اسم ام مرت سليمان    ؟",
                mutableListOf("ددوجه", "جنات ", "دوجه "),
                correct = "دوجه") )



        listQuestionModel.add(
            QuizModel.QuestionModel(
                "قاش يخلص السبوعي في الشهر  ؟",
                mutableListOf("120", "220", "180"),
                correct = "120") )


        listQuestionModel.add(
            QuizModel.QuestionModel(
                "وقتاش بدت سلسله شوفلي حل  ؟",
                mutableListOf("2006", "2008", "2005"),
                correct = "2005") )



        listQuestionModel.add(
            QuizModel.QuestionModel(
                "ما هي هواية السبوعي    ؟",
                mutableListOf("الاكل", "النوم", "العمل"),
                correct = "الاكل") )

        listQuestionModel.add(
            QuizModel.QuestionModel(
                "شنيا اسم مرت سليمان    ؟",
                mutableListOf("عزه", "مرت سيدي خويا", "زينب "),
                correct = "الاكل") )


        quizModelList.add(QuizModel(id="1", title = "niveau 0" , description = "السبوعي و عزه", time="8",listQuestionModel))
      //  quizModelList.add(QuizModel(id="2", title = "niveau 1" , description = "saison 1", time="7"))
      //  quizModelList.add(QuizModel(id="3", title = "niveau 2" , description = "saison 3", time="6"))
      //  quizModelList.add(QuizModel(id="4", title = "niveau 3" , description = "سليمان", time="6"))
      //  quizModelList.add(QuizModel(id="5", title = "niveau 4" , description = "جنات", time="3"))
      //  quizModelList.add(QuizModel(id="6", title = "niveau 5" , description = "الباجي", time="3"))
        setupRecyclerView()
    }
}