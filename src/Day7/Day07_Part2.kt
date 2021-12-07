package Day7

import readInput
import java.math.BigInteger
import kotlin.collections.ArrayList
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.floor

fun main() {

    fun calcSum(a:Int):Int
    {
        return (a*(a+1))/2
    }

    fun part2(input: List<String>): Int {
        val fishes = input[0].split(",").map { it-> it.trim().toInt() }.toMutableList()

        fishes.sort()

        var avg = 0

        for(item in fishes)
        {
            avg+=item
        }

        val avg1 = floor(avg.toDouble()/fishes.size).toInt()
        val avg2 = ceil(avg.toDouble()/fishes.size).toInt()

        var ans1=0
        var ans2=0

        for(item in fishes)
        {
            ans1+= calcSum(abs(item-avg1))
            ans2+= calcSum(abs(item-avg2))
        }

        return minOf(ans1,ans2)

    }


    val testInput = readInput("Day7/Day07_test")
    val input = readInput("Day7/Day07")

    println(part2(testInput))
    println(part2(input))
}