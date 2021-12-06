package Day6

import readInput
import java.math.BigInteger
import kotlin.collections.ArrayList

fun main() {

    fun part2(input: List<String>): BigInteger {
        val fishes:ArrayList<Int> = arrayListOf()

        for(item in input[0].split(",").map { it.trim().toInt() })
        {
            fishes.add(item)
        }

        val prevArray: MutableList<BigInteger> = List(9){0.toBigInteger()}.toMutableList()

        for(item in fishes)
        {
            prevArray[item]=prevArray[item].plus(1.toBigInteger())
        }

        //println(prevArray)



        repeat(256)
        {
            val currArray:MutableList<BigInteger> = List(9){0.toBigInteger()}.toMutableList()

            currArray[6]+=prevArray[0]
            currArray[8]+=prevArray[0]

            for(i in 1..8)
            {
                currArray[i-1]+= prevArray[i]
            }

            for(i in 0..8) {
                prevArray[i] = currArray[i]
            }
        }

        var ans:BigInteger = 0.toBigInteger()


        for(item in prevArray)
        {
            ans = ans.plus(item)
        }

        return ans
    }


    val testInput = readInput("Day6/Day06_test")
    val input = readInput("Day6/Day06")

    println(part2(testInput))
    println(part2(input))
}