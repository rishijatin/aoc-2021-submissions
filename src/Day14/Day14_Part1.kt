package Day14

import readInput
import kotlin.math.max
import kotlin.math.min


fun main() {


    fun addProcedure(mapping:MutableMap<String,String>,polymer:MutableList<String>)
    {
        val added:MutableList<String> = mutableListOf()

        for(i in 1 until polymer.size)
        {
            added.add(mapping[polymer[i-1]+polymer[i]]!!)
        }

        var cntr=1
        for(i in added.indices)
        {
            polymer.add(cntr,added[i])
            cntr+=2
        }
    }

    fun part1(input: List<String>): Int {
        var ans= 0

        val polymer = input[0].trim().map { it.toString() }.toMutableList()

        val mapping:MutableMap<String,String> = mutableMapOf()

        for(i in 2 until input.size)
        {
            var(a,b)  =input[i].split("->")
            a= a.trim()
            b= b.trim()
            mapping[a] = b
        }

        repeat(10){
            addProcedure(mapping,polymer)
        }
        val freq:MutableMap<String,Int> = mutableMapOf()

        for(item in polymer)
        {
            freq[item]=freq[item]?.plus(1)?:1
        }

        var maxi=0
        var mini=Int.MAX_VALUE

        for((_,value) in freq)
        {
            maxi = max(maxi,value)
            mini = min(mini,value)
        }


        return maxi-mini
    }


    val testInput = readInput("Day14/Day14_test")
    val input = readInput("Day14/Day14")

    println(part1(testInput))
    println(part1(input))
}