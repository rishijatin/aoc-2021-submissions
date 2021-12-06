package Day6

import readInput
fun main() {

    fun part1(input: List<String>): Int {

        val fishes:ArrayList<Int> = arrayListOf()

        for(item in input[0].split(",").map { it.trim().toInt() })
        {
            fishes.add(item)
        }

        repeat(80){
        for(i in fishes.size-1 downTo 0)
        {
            if(fishes[i]==0)
            {
                fishes.add(8)
                fishes[i]=6
            }
            else
            {
                fishes[i]--
            }
        }
        }

        return fishes.size
    }


    val testInput = readInput("Day6/Day06_test")
    val input = readInput("Day6/Day06")

    println(part1(testInput))
    println(part1(input))
}