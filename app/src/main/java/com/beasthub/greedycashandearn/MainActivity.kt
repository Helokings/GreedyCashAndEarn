package com.beasthub.greedycashandearn

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.beasthub.greedycashandearn.adapter.CoinsAdapter
import com.beasthub.greedycashandearn.adapter.ItemAdapter
import com.beasthub.greedycashandearn.databinding.ActivityMainBinding
import com.beasthub.greedycashandearn.model.ApiViewModel
import com.beasthub.greedycashandearn.sharedprefsection.Constant
import com.bumptech.glide.Glide
import com.example.networking.socket.SocketClient
import com.example.networking.utils.ApiCallState
import dagger.hilt.android.AndroidEntryPoint
import io.socket.client.IO
import io.socket.client.Socket
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.net.URISyntaxException

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val handler = Handler()
    private var currentIndex = 0
    private var resultindex = 0
    private var isBlinking = false
    private var tken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJtc2ciOiJ2ZXJpZmljYXRpb24gc3VjY2Vzc2Z1bGx5Iiwic3RhdHVzIjoidHJ1ZSIsInZhbGlkaXR5IjoxLCJ1c2VyX2lkIjoiMTQwMyIsImZpcnN0X25hbWUiOiJkZXZpbCBcdWQ4M2RcdWRjN2ZcdWQ4M2RcdWRjN2ZcdWQ4M2RcdWRjN2YiLCJsYXN0X25hbWUiOiIiLCJlbWFpbCI6IiIsInBob25lIjoiODgwODc2OTI1NyIsInVzZXJuYW1lIjoiNTk2OTY5IiwicHJvZmlsZV9jb21wbGV0ZSI6IjEiLCJyb2xlIjoidXNlciIsInJlZmVyX2NvZGUiOiJNT044MjM3OCIsIndhbGxldCI6IjQ0NzIiLCJzZXNzaW9uX2lkIjoidXBhMTI4bm4zOTQ0ZmQ3c3E1MGYyOGZnazJob280Z3UiLCJuaWNrX25hbWUiOiJkZXZpbCBcdWQ4M2RcdWRjN2ZcdWQ4M2RcdWRjN2ZcdWQ4M2RcdWRjN2YifQ.ANyfs5CbhT6PS2lML4Ik47EmtG7GxJSCSXoPozGUiRU"
    private var imageViews: List<ImageView> = listOf(

    )

    private var linearLayouts: List<LinearLayout> = listOf(

    )

    private var textViews: List<TextView> = listOf(

    )


    private lateinit var countDownTimer: CountDownTimer

    private var isBettingOn = false
    private var isSpinningon = false
    private var timeleft1 = "false"

    private lateinit var socketClient: SocketClient

    lateinit var binding: ActivityMainBinding
    val apiViewModel: ApiViewModel by viewModels()
    var game_type = 1
    var detected_amount: Long = 0
    private var TotalAmount: Long? = null
    private var isCountingDown = false
    private lateinit var mSocket: Socket


    private var mediaPlayer: MediaPlayer? = null

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        Glide.with(this@MainActivity) // Use 'this' for the activity or 'requireContext()' for a fragment
            .load(R.drawable.popper)
            .placeholder(R.drawable.bluegradient) // Optional placeholder image while loading
//                            .error(R.drawable.error) // Optional error image if the load fails
            .into(binding.winnergif)
        try {
            // Replace "http://your_server_address" with your server URL
            mSocket = IO.socket("http://13.232.243.193:8000/")
            mSocket.connect()
        } catch (e: URISyntaxException) {
            e.printStackTrace()
        }
        walletAmount()
        coinsList()
        val itemList = listOf(
            Item("Neha", 1, 5000),
            Item("Anil", 2, 3000),
            Item("Komal", 3, 1000),
            // Add more items as needed
        )

        val adapter = ItemAdapter(itemList)

        // Initialize SocketClient with the ConnectionStateListener
        mediaPlayer = MediaPlayer.create(
            this,
            R.raw.rsult
        ) // Replace "your_audio_file" with your audio file name


        binding.rules.setOnClickListener {
            val intent = Intent(this, RulesFragment::class.java)
            startActivity(intent)

        }
        // Create a list of your views using View Binding
        imageViews = listOf(
            binding.boxpublb1,
            binding.boxpublb2,
            binding.boxpublb3,
            binding.boxpublb4,
            binding.boxpublb5,
            binding.boxpublb6,
            binding.boxpublb7,
            binding.boxpublb8
        )

        // Start blinking
        setClickListenersForLinearLayouts()
        binding.timer.setText("Betting Time ")

        mSocket.on(Socket.EVENT_CONNECT) {
            // Handle connection event
            Log.e("fhygujkl", "drtfgyh");
        }


        mSocket.on("gameState") { args ->
            if (args[0] is JSONObject) {
                val gameStateData = args[0] as JSONObject
                // Now you can access the data in gameStateData
                // For example, if there's a field named 'score' in your JSON
                Log.e("fhygujkl", "drtfgyh" + gameStateData);
                val status = gameStateData.getBoolean("status")
                val message = gameStateData.getString("msg")
                val timeLeft = gameStateData.getInt("timeLeft")
                val phase = gameStateData.getString("phase")
//                val bets = gameStateData.getJSONArray("bets") // This is an array
                val currentSlotId = gameStateData.getString("currentSlotId")
//                        val gameEnd = gameStateData.getString("gameEnd")
                // You can update UI elements or perform actions based on this data
                // Make sure to run UI updates on the main thread


                if (status) {


                    runOnUiThread {
                        if (phase.equals("BETTING")) {
                            imageViews[resultindex].clearColorFilter();

                            isBettingOn = true

                            binding.winnergif.visibility=View.GONE
                            binding.stopblinkjng.visibility=View.VISIBLE

                            isBlinking = false
                            stopIndex = -1
                            binding.timer.setText("Betting Time : " + (timeLeft - 30) + " sec")
                            binding.stopblinkjng.setText("" + (timeLeft - 30))


                        }


                        if (phase.equals("NO_BETTING")) {


                            if (timeLeft < 30) {

                                if (!isBlinking) {
                                    isBlinking = true

                                    startBlinking(imageViews)

                                }

                            }



                            timeleft1 = timeLeft.toString()

                            isBettingOn = false

                            binding.timer.setText("Result in : " + (timeLeft-5) + " sec")
                            binding.stopblinkjng.setText("Wait..")

                        }
                        // Update your UI elements here
                        // e.g., textViewScore.text = "Score: $score"
                    }
                }else{
                     Toast.makeText(this@MainActivity, ""+message, Toast.LENGTH_SHORT)
                        .show()


                }
            }
        }

        mSocket.on("validationerror") { args ->
            if (args[0] is JSONObject) {
                val gameStateData = args[0] as JSONObject
                // Now you can access the data in gameStateData
                // For example, if there's a field named 'score' in your JSON
                Log.e("fhygujkl", "drtfgyh" + gameStateData);
                val status = gameStateData.getBoolean("status")
                val message = gameStateData.getString("msg")





                    runOnUiThread {


                     Toast.makeText(this@MainActivity, ""+message, Toast.LENGTH_SHORT)
                        .show()


                }
            }
        }

        mSocket.on("winnerDeclared") { args ->
            if (args[0] is JSONObject) {
                val gameStateData = args[0] as JSONObject
                // Now you can access the data in gameStateData
                // For example, if there's a field named 'score' in your JSON
                Log.e("sdfgserdfchjdtrtfcg", "drtfgyh" + gameStateData);

                val status = gameStateData.getBoolean("status")
                val msg = gameStateData.getString("msg")
                val winnerJson = gameStateData.getJSONObject("winner")

                val top3user = gameStateData.getJSONArray("top3Users") // This is an array

                val userId = winnerJson.getString("userId")
                val amount = winnerJson.getInt("amount")
                val spinNumber = winnerJson.getInt("spinNumber")
                runOnUiThread {

                    isBlinking = false
                    mediaPlayer?.start()


                    binding.winnergif.visibility=View.VISIBLE
                    binding.stopblinkjng.visibility=View.GONE


                    resultindex = spinNumber - 1
                    stopBlinking(resultindex);

                    resettextview()
//


                }
            }
        }


        mSocket.on(Socket.EVENT_DISCONNECT) {
            // Handle disconnect event
        }


    }

    private fun resettextview() {

        for (i in linearLayouts.indices) {

            val linearLayout = linearLayouts[i]
            val textView = textViews[i]

            clickCounts[i] = 0
            textView.text = ""


        }
    }

    private fun setClickListenersForLinearLayouts() {


        linearLayouts = listOf(
            binding.box1,
            binding.box2,
            binding.box3,
            binding.box4,
            binding.box5,
            binding.box6,
            binding.box7,
            binding.box8
        )

        textViews = listOf(
            binding.txtGreedyBidAmount1,
            binding.txtGreedyBidAmount2,
            binding.txtGreedyBidAmount3,
            binding.txtGreedyBidAmount4,
            binding.txtGreedyBidAmount5,
            binding.txtGreedyBidAmount6,
            binding.txtGreedyBidAmount7,
            binding.txtGreedyBidAmount8
        )

        for (i in linearLayouts.indices) {
            val linearLayout = linearLayouts[i]
            val textView = textViews[i]

            linearLayout.setOnClickListener {
                // Increment the click count and display it in the TextView
                if (isBettingOn) {
                    if (additionalAmount == 0) {
                        Toast.makeText(this@MainActivity, "Please coin amount", Toast.LENGTH_SHORT)
                            .show()
//

                    } else {

                        clickCounts[i] += additionalAmount
                        textView.text = "Coin: ${clickCounts[i]} "
                        Toast.makeText(this@MainActivity, "" + i, Toast.LENGTH_SHORT)

                        placeBet(tken, "201929",i + 1, additionalAmount)


                    }

                } else {
                    Toast.makeText(this@MainActivity, "Wait or Betting Time", Toast.LENGTH_SHORT)
                        .show()
                }

            }
        }
    }


    fun placeBet(authtoken: String ,userId: String, spinNumber: Int, amount: Int) {
        val betData = JSONObject().apply {
            put("userId", userId)
            put("auth_token", authtoken)
            put("spinNumber", spinNumber)
            put("amount", amount)
        }

        mSocket.emit("placeBet", betData)
    }

    var additionalAmount = 0 // You can customize this amount

    private val clickCounts = IntArray(8) // Assuming you have 8 LinearLayouts

    private fun coinsList() = CoroutineScope(Dispatchers.Main).launch {
        apiViewModel.greedyList(Constant.token, game_type.toString()).collectLatest {
            when (it) {
                is ApiCallState.Error -> {
                    Log.d("Error", "coinsList: ${it.error}")
                }
                ApiCallState.Loading -> {}
                is ApiCallState.Success -> {
                    Log.d("Coin", "coinsList: ${it.data.coinData}")
                    if (it.data!!.status.equals("true", ignoreCase = true)) {
                        binding.coinsRecyclerView.layoutManager = LinearLayoutManager(
                            this@MainActivity, LinearLayoutManager.HORIZONTAL, false
                        )

                        Glide.with(this@MainActivity) // Use 'this' for the activity or 'requireContext()' for a fragment
                            .load(it.data.data.get(0).image)
                            .placeholder(R.drawable.bluegradient) // Optional placeholder image while loading
//                            .error(R.drawable.error) // Optional error image if the load fails
                            .into(binding.layoutGreedyBox1)


                        Glide.with(this@MainActivity) // Use 'this' for the activity or 'requireContext()' for a fragment
                            .load(it.data.data.get(1).image)
                            .placeholder(R.drawable.bluegradient) // Optional placeholder image while loading
//                            .error(R.drawable.error) // Optional error image if the load fails
                            .into(binding.layoutGreedyBox2)


                        Glide.with(this@MainActivity) // Use 'this' for the activity or 'requireContext()' for a fragment
                            .load(it.data.data.get(2).image)
                            .placeholder(R.drawable.bluegradient) // Optional placeholder image while loading
//                            .error(R.drawable.error) // Optional error image if the load fails
                            .into(binding.layoutGreedyBox3)


                        Glide.with(this@MainActivity) // Use 'this' for the activity or 'requireContext()' for a fragment
                            .load(it.data.data.get(3).image)
                            .placeholder(R.drawable.bluegradient) // Optional placeholder image while loading
//                            .error(R.drawable.error) // Optional error image if the load fails
                            .into(binding.layoutGreedyBox4)


                        Glide.with(this@MainActivity) // Use 'this' for the activity or 'requireContext()' for a fragment
                            .load(it.data.data.get(4).image)
                            .placeholder(R.drawable.bluegradient) // Optional placeholder image while loading
//                            .error(R.drawable.error) // Optional error image if the load fails
                            .into(binding.layoutGreedyBox5)


                        Glide.with(this@MainActivity) // Use 'this' for the activity or 'requireContext()' for a fragment
                            .load(it.data.data.get(5).image)
                            .placeholder(R.drawable.bluegradient) // Optional placeholder image while loading
//                            .error(R.drawable.error) // Optional error image if the load fails
                            .into(binding.layoutGreedyBox6)


                        Glide.with(this@MainActivity) // Use 'this' for the activity or 'requireContext()' for a fragment
                            .load(it.data.data.get(6).image)
                            .placeholder(R.drawable.bluegradient) // Optional placeholder image while loading
//                            .error(R.drawable.error) // Optional error image if the load fails
                            .into(binding.layoutGreedyBox7)


                        Glide.with(this@MainActivity) // Use 'this' for the activity or 'requireContext()' for a fragment
                            .load(it.data.data.get(7).image)
                            .placeholder(R.drawable.bluegradient) // Optional placeholder image while loading
//                            .error(R.drawable.error) // Optional error image if the load fails
                            .into(binding.layoutGreedyBox8)

///////////////////////////////////////////////////////////////////////////////////////////////


                        binding.times1.setText(it.data.data.get(0).times + " times") // Set text using the setText() method
                        binding.times2.setText(it.data.data.get(1).times + " times") // Set text using the setText() method
                        binding.times3.setText(it.data.data.get(2).times + " times") // Set text using the setText() method
                        binding.times4.setText(it.data.data.get(3).times + " times") // Set text using the setText() method
                        binding.times5.setText(it.data.data.get(4).times + " times") // Set text using the setText() method
                        binding.times6.setText(it.data.data.get(5).times + " times") // Set text using the setText() method
                        binding.times7.setText(it.data.data.get(6).times + " times") // Set text using the setText() method
                        binding.times8.setText(it.data.data.get(7).times + " times") // Set text using the setText() method


                        ////////////////////////////////////////////////////////////////////


                        binding.coinsRecyclerView.adapter =

                            CoinsAdapter(this@MainActivity,
                                it.data.coinData,
                                object : CoinsAdapter.DetectedAmountCallback {
                                    override fun spentAmount(amount: String, position: Int) {
                                        additionalAmount = amount.toInt()
                                        binding?.walletAmount?.text =
                                            "Wallet Balance : " + (TotalAmount!! - amount.toLong()).toString()

                                        TotalAmount = (TotalAmount!! - amount.toLong())

                                        detected_amount = detected_amount + amount.toLong()

                                        if (TotalAmount.toString().toLong() < 50) {
                                            val dialog = Dialog(this@MainActivity)
                                            dialog.setContentView(R.layout.custom_dialog)

                                            val dialogMessage =
                                                dialog.findViewById<TextView>(R.id.dialogMessage)
                                            val dialogMessagetwo =
                                                dialog.findViewById<TextView>(R.id.dialogMessagetwo)
                                            val dialogButton =
                                                dialog.findViewById<Button>(R.id.dialogButton)

                                            dialogMessage.text = "Low Wallet Balance"
                                            dialogMessagetwo.text = "Recharge Now!"

                                            dialogButton.setOnClickListener {
                                                dialog.dismiss()

                                            }

                                            dialog.show()
                                        }
                                    }

                                })
                    }
                }
            }
        }
    }


    private fun walletAmount() = CoroutineScope(Dispatchers.Main).launch {
        apiViewModel.walletData(Constant.token).collectLatest {
            when (it) {
                is ApiCallState.Error -> {
                    Log.d("Error", "coinsList: ${it.error}")
                }
                ApiCallState.Loading -> {}
                is ApiCallState.Success -> {
                    Log.d("WalletData", "coinsList: ${it.data.data}")
                    if (it.data!!.status.equals("true", ignoreCase = true)) {
                        it.data.data.apply {
                            binding.walletAmount.text = "Wallet Balance : " + wallet
                            TotalAmount = wallet.toLong()

                        }
                    }
                }
                else -> {

                }
            }
        }
    }

    private var stopIndex = -1

    private fun startBlinking(imageViews: List<ImageView>) {


        if (currentIndex < imageViews.size) {
            val imageViewToBlink = imageViews[currentIndex]


            // Reset the previous ImageView to its normal state
            resetImageView(imageViews, currentIndex - 1)

            // If currentIndex matches the stopIndex, stop blinking
            if (currentIndex == stopIndex) {
                toggleTint(imageViewToBlink)
                return
            }
            // Change the color of the current ImageView
            toggleTint(imageViewToBlink)


            currentIndex++

            // Schedule the next ImageView to blink after a delay (e.g., 1000ms or 1 second)
            handler.postDelayed({ startBlinking(imageViews) }, 300)
        } else {
            // Reset currentIndex when all ImageViews have been processed
            currentIndex = 0

            // Reset the last ImageView before starting a new round
            resetImageView(imageViews, imageViews.size - 1)

            // Restart blinking loop after a delay (e.g., 1000ms or 1 second)
            handler.postDelayed({ startBlinking(imageViews) }, 300)
        }
    }


    private fun toggleTint(imageView: ImageView) {


        val blinkColor = ContextCompat.getColor(this, R.color.colorOn) // Define your blink color
        imageView.setColorFilter(blinkColor)


    }


    private fun resetImageView(imageViews: List<ImageView>, index: Int) {
        if (index >= 0 && index < imageViews.size) {
            val imageViewToReset = imageViews[index]

            // Clear the color filter to reset to the normal state
            imageViewToReset.clearColorFilter()
        }
    }

    private fun stopBlinking(indexToStop: Int) {
        stopIndex = indexToStop

    }

    override fun onDestroy() {
        super.onDestroy()
        // Ensure that blinking is stopped when the activity is destroyed
    }


}



