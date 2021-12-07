package Day7

import readInput
import kotlin.math.abs

fun main() {


    fun getMedian(fishes:MutableList<Int>):List<Int>
    {
        return if(fishes.size%2==0) {
            val mid1 = fishes.size/2
            val mid2 = (fishes.size/2) - 1

            listOf(fishes[mid2],fishes[mid1])
        } else {
            val mid = fishes.size/2
            listOf(fishes[mid],fishes[mid])
        }
    }

    fun part1(input: List<String>): Int {

        val fishes = input[0].split(",").map { it-> it.trim().toInt() }.toMutableList()

        fishes.sort()

        val (md1,md2) = getMedian(fishes)

        var op1 = 0
        var op2 = 0

        for(item in fishes)
        {
            op1+= abs(item-md1)
        }
        for(item in fishes)
        {
            op2+=abs(item-md2)
        }

        return minOf(op1,op2)
    }


    val testInput = readInput("Day7/Day07_test")
    val input = readInput("Day7/Day07")

    println(part1(testInput))
    println(part1(input))
}