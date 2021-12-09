package Day8

import readInput

fun main() {


    fun part1(input: List<String>): Int {

        var ans=0

        for(item in input)
        {
            var(_,second) = item.split("|")
            second = second.trim()

            val codes = second.split(" ").toMutableList()
            for(i in codes.indices)
            {
                codes[i] = codes[i].trim()
            }

            for(item in codes)
            {
                if(item.length==2 || item.length==4 || item.length==7 || item.length==3)
                {
                    ans++
                }
            }

        }

        return ans
    }


    val testInput = readInput("Day8/Day08_test")
    val input = readInput("Day8/Day08")

    println(part1(testInput))
    println(part1(input))
}