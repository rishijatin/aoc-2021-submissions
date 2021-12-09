package Day9

import readInput


fun main() {

    fun isHigh(_i:Int,_j:Int,i:Int,j:Int,input:List<String>):Boolean
    {
        if(i<0 || i>=input.size) return true
        if(j<0 || j>=input[0].length) return true

        if(input[i][j]-'0'>input[_i][_j]-'0') return true

        return false


    }

    fun isLow(i:Int,j:Int,input:List<String>):Boolean
    {
        val a = if(isHigh(i,j,i-1,j,input))  1 else 0
        val b = if(isHigh(i,j,i+1,j,input)) 1 else 0
        val c = if(isHigh(i,j,i,j-1,input)) 1 else 0
        val d = if(isHigh(i,j,i,j+1,input)) 1 else 0

        if(a+b+c+d==4) return true
        return false

    }

    fun part1(input: List<String>): Int {

        var ans=0

        for(i in input.indices)
        {
            for(j in input[0].indices)
            {
                if(isLow(i,j,input))
                {
                    ans+= input[i][j].toString().toInt()+1
                }
            }
        }

        return ans
    }


    val testInput = readInput("Day9/Day09_test")
    val input = readInput("Day9/Day09")

    println(part1(testInput))
    println(part1(input))
}