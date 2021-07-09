package com.colors.simonsays.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.colors.simonsays.R
import com.colors.simonsays.ui.main.GameUtils.BLUE
import com.colors.simonsays.ui.main.GameUtils.COLOR
import com.colors.simonsays.ui.main.GameUtils.EMPTY
import com.colors.simonsays.ui.main.GameUtils.GAME_OVER
import com.colors.simonsays.ui.main.GameUtils.GREEN
import com.colors.simonsays.ui.main.GameUtils.MAX_ROUNDS
import com.colors.simonsays.ui.main.GameUtils.ORANGE
import com.colors.simonsays.ui.main.GameUtils.PURPLE
import com.colors.simonsays.ui.main.GameUtils.RESTART
import com.colors.simonsays.ui.main.GameUtils.SIMON_SAYS
import com.colors.simonsays.ui.main.GameUtils.YOU_WIN
import kotlin.random.Random

class
MainViewModel : ViewModel() {

    private val colorNames = listOf(PURPLE, ORANGE, GREEN, BLUE, COLOR)
    private val listOfBackgroundColors = listOf(
        R.color.purple_background_color,
        R.color.orange_background_color,
        R.color.green_background_color,
        R.color.blue_background_color
    )
    private val hashMapBackgroundColors = LinkedHashMap<String, Int>()
    private var _currentColor: String = EMPTY
    private var _currentBackgroundColor: String = EMPTY
    private var rounds = 1

    /**
     * This method will return Simon says text randomly
     */
    private var _level: MutableLiveData<String> = MutableLiveData()
    val level: LiveData<String> = _level
    private fun updateLevel() {
        _level.value = String.format("%s: %s/%s", "Level", "$rounds", "$MAX_ROUNDS")
    }

    /**
     * This method will return Simon says text randomly
     */
    private var getSimonSays: MutableLiveData<String> = MutableLiveData()
    val simonSays: LiveData<String> = getSimonSays

    private fun getSimonSays(): String {
        val random = Random.nextInt(0, 5)
        _currentColor = colorNames[random]
        if (_currentColor == COLOR) {
            // Set any random color
            updateBackgroundColor()
        } else {
            _currentBackgroundColor = _currentColor
            updateBackgroundColor()


        // Set the same background color as text
            /*_currentBackgroundColor = _currentColor
            setBackgroundColor.value = hashMapBackgroundColors[_currentColor]
            */

        }
        val saying = String.format("%s: %s", SIMON_SAYS, _currentColor)
        getSimonSays.value = saying
        return saying
    }

    /**
     * This method will return Simon says text randomly
     */
    private val setBackgroundColor: MutableLiveData<Int> = MutableLiveData()

    val backgroundColor: LiveData<Int> = setBackgroundColor

    private fun getRandomBackgroundColor(): Int {
        val random = Random.nextInt(0, 4)
        _currentBackgroundColor = colorNames[random]
        return listOfBackgroundColors[random]
    }

    private fun updateBackgroundColor() {
        setBackgroundColor.value = getRandomBackgroundColor()
    }


    /**
     * Button 1
     * Note: Whenever a button is clicked we have to check if it is
     * set to YOU_WIN or GAME_OVER
     *
     * If button = RESTART or current color/background color selection is set to EMPTY, that means
     * button is in RESTART state so restart the game.
     *
     * We need to check if current displayed color name is same as button color
     * or if Simon is saying color and the background color matches the button color --> that means it's a correct answer
     * at this point, if we're done with all 20 rounds, the user WON otherwise
     *
     * it's a wrong decision GAMER OVER!!
     *
     * ALl buttons follow this logic upon click.
     *
     */
    private val _textColorButton1: MutableLiveData<Int> = MutableLiveData()
    val textColorButton1: LiveData<Int> = _textColorButton1
    private val _textButton1: MutableLiveData<String> = MutableLiveData()
    val textButton1: LiveData<String> = _textButton1
    fun clickButton1() {
        if (_textButton1.value == YOU_WIN || _textButton1.value == GAME_OVER) return
        if (_textButton1.value == RESTART && _currentColor == EMPTY && _currentBackgroundColor == EMPTY) {
            //Restart game
            restartGame()
        } else {
            //Evaluate click
            if (_currentColor == PURPLE || (_currentColor == COLOR && _currentBackgroundColor == PURPLE)) {
                rounds += 1
                updateLevel()
                if (rounds >= MAX_ROUNDS) {
                    // You won all levels cleared
                    _textButton1.value = RESTART
                    _textButton2.value = YOU_WIN
                    _textButton3.value = YOU_WIN
                    _textButton4.value = YOU_WIN
                    _textColorButton1.value = R.color.black
                    _currentColor = EMPTY
                    _currentBackgroundColor = EMPTY
                    rounds = 1
                } else {
                    restartGame()
                }
            } else {
                _textButton2.value = GAME_OVER
                _textButton3.value = GAME_OVER
                _textButton4.value = GAME_OVER
                _textColorButton1.value = R.color.white
                _textButton1.value = RESTART
                rounds = 1
                _currentColor = EMPTY
                _currentBackgroundColor = EMPTY
            }
        }
    }

    /**
     * Button 2
     */
    private val _textColorButton2: MutableLiveData<Int> = MutableLiveData()
    val textColorButton2: LiveData<Int> = _textColorButton2
    private val _textButton2: MutableLiveData<String> = MutableLiveData()
    val textButton2: LiveData<String> = _textButton2
    fun clickButton2() {
        if (_textButton2.value == YOU_WIN || _textButton2.value == GAME_OVER) return
        if (_textButton2.value == RESTART && _currentColor == EMPTY && _currentBackgroundColor == EMPTY) {
            //Restart game
            restartGame()
        } else {
            //Evaluate click
            if (_currentColor == ORANGE || (_currentColor == COLOR && _currentBackgroundColor == ORANGE)) {
                rounds += 1
                updateLevel()
                if (rounds == MAX_ROUNDS) {
                    // You won all levels cleared
                    _textButton2.value = RESTART
                    _textButton1.value = YOU_WIN
                    _textButton3.value = YOU_WIN
                    _textButton4.value = YOU_WIN
                    _textColorButton2.value = R.color.black
                    _currentColor = EMPTY
                    _currentBackgroundColor = EMPTY
                    rounds = 1
                } else {
                    //Restart game
                    restartGame()
                }
            } else {
                _textButton1.value = GAME_OVER
                _textButton3.value = GAME_OVER
                _textButton4.value = GAME_OVER
                _textColorButton2.value = R.color.white
                _textButton2.value = RESTART
                rounds = 1
                _currentColor = EMPTY
                _currentBackgroundColor = EMPTY
            }
        }
    }

    /**
     * Button 3
     */
    private val _textColorButton3: MutableLiveData<Int> = MutableLiveData()
    val textColorButton3: LiveData<Int> = _textColorButton3
    private val _textButton3: MutableLiveData<String> = MutableLiveData()
    val textButton3: LiveData<String> = _textButton3
    fun clickButton3() {
        if (_textButton3.value == YOU_WIN || _textButton3.value == GAME_OVER) return
        if (_textButton3.value == RESTART && _currentColor == EMPTY && _currentBackgroundColor == EMPTY) {
            //Restart game
            restartGame()
        } else {
            //Evaluate click
            if (_currentColor == GREEN || (_currentColor == COLOR && _currentBackgroundColor == GREEN)) {
                rounds += 1
                updateLevel()
                if (rounds == MAX_ROUNDS) {
                    // You won all levels cleared
                    _textButton3.value = RESTART
                    _textButton1.value = YOU_WIN
                    _textButton2.value = YOU_WIN
                    _textButton4.value = YOU_WIN
                    _textColorButton3.value = R.color.black
                    _currentColor = EMPTY
                    _currentBackgroundColor = EMPTY
                    rounds = 1
                } else {
                    //Restart game
                    restartGame()
                }
            } else {
                _textButton1.value = GAME_OVER
                _textButton2.value = GAME_OVER
                _textButton4.value = GAME_OVER
                _textColorButton3.value = R.color.white
                _textButton3.value = RESTART
                rounds = 1
                _currentColor = EMPTY
                _currentBackgroundColor = EMPTY
            }
        }
    }

    /**
     * Button 4
     */
    private val _textColorButton4: MutableLiveData<Int> = MutableLiveData()
    val textColorButton4: LiveData<Int> = _textColorButton4
    private val _textButton4: MutableLiveData<String> = MutableLiveData()
    val textButton4: LiveData<String> = _textButton4
    fun clickButton4() {
        if (_textButton4.value == YOU_WIN || _textButton4.value == GAME_OVER) return
        if (_textButton4.value == RESTART && _currentColor == EMPTY && _currentBackgroundColor == EMPTY) {
            //Restart game
            restartGame()
        } else {
            //Evaluate click
            if (_currentColor == BLUE || (_currentColor == COLOR && _currentBackgroundColor == BLUE)) {
                rounds += 1
                updateLevel()
                if (rounds == MAX_ROUNDS) {
                    // You won all levels cleared
                    _textButton4.value = RESTART
                    _textButton1.value = YOU_WIN
                    _textButton2.value = YOU_WIN
                    _textButton3.value = YOU_WIN
                    _textColorButton4.value = R.color.black
                    _currentColor = EMPTY
                    _currentBackgroundColor = EMPTY
                    rounds = 1
                } else {
                    //Restart game
                    restartGame()
                }
            } else {
                _textButton1.value = GAME_OVER
                _textButton2.value = GAME_OVER
                _textButton3.value = GAME_OVER
                _textColorButton4.value = R.color.white
                _textButton4.value = RESTART
                rounds = 1
                _currentColor = EMPTY
                _currentBackgroundColor = EMPTY
            }
        }
    }


    /**
     * Restart game
     */
    private fun restartGame() {
        resetButtonTextColors()

        resetButtonTexts()

        //Start game by asking what simon says
        getSimonSays()

        updateLevel()
    }


    /**
     * Reset button text colors to white
     */
    private fun resetButtonTextColors() {
        _textColorButton1.value = R.color.white
        _textColorButton2.value = R.color.white
        _textColorButton3.value = R.color.white
        _textColorButton4.value = R.color.white
    }

    /**
     * Reset all buttons colors to empty
     */
    private fun resetButtonTexts() {
        _textButton1.value = EMPTY
        _textButton2.value = EMPTY
        _textButton3.value = EMPTY
        _textButton4.value = EMPTY
    }



    init {
        listOfBackgroundColors.forEachIndexed { index, element ->
            hashMapBackgroundColors[colorNames[index]] = element
        }

        updateLevel()

        resetButtonTextColors()

        resetButtonTexts()

        //Start game by asking what simon says
        getSimonSays()
    }

}