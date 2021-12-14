package Day14

import readInput
import kotlin.math.ceil
import kotlin.math.max
import kotlin.math.min


fun main() {

    fun addProcedure(mapping:MutableMap<String,String>,currPairs:MutableMap<String,Long>):MutableMap<String,Long>
    {
        val newPairs:MutableMap<String,Long> = mutableMapOf()

        for((key,value) in currPairs)
        {
            val mappingValue = mapping[key]!!
            newPairs[key[0]+mappingValue] = newPairs[key[0]+mappingValue]?.plus(value)?:value
            newPairs[mappingValue+key[1]] = newPairs[mappingValue+key[1]]?.plus(value)?:value
        }

        return newPairs
    }

    fun part2(input: List<String>): Long {

        val polymer = input[0].trim().map { it.toString() }.toMutableList()

        val mapping:MutableMap<String,String> = mutableMapOf()

        for(i in 2 until input.size)
        {
            var(a,b)  =input[i].split("->")
            a= a.trim()
            b= b.trim()
            mapping[a] = b
        }

        var curr_pairs:MutableMap<String,Long> = mutableMapOf()

        for(i in 1 until polymer.size)
        {
            curr_pairs[polymer[i-1]+polymer[i]] = curr_pairs[polymer[i-1]+polymer[i]]?.plus(1)?:1
        }

        println(curr_pairs)

        repeat(40){
            curr_pairs = addProcedure(mapping,curr_pairs)
        }
        val freq:MutableMap<String,Long> = mutableMapOf()

        for((key,value) in curr_pairs)
        {
            freq[key[0].toString()] = freq[key[0].toString()]?.plus(value)?:value
            freq[key[1].toString()] = freq[key[1].toString()]?.plus(value)?:value
        }

        for((key,value ) in freq)
        {
            freq[key] = ceil(freq[key]?.div(2.0) ?: 0.0).toLong()
        }

       println(freq.toString())

        var maxi=0L
        var mini=Long.MAX_VALUE

        for((_,value) in freq)
        {
            maxi = max(maxi,value)
            mini = min(mini,value)
        }

        return maxi-mini
    }


    val testInput = readInput("Day14/Day14_test")
    val input = readInput("Day14/Day14")

    println(part2(testInput))
    println(part2(input))
}