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

    fun dfs(input:List<String>,i:Int,j:Int,visited:MutableSet<Pair<Int,Int>>):Int
    {
        if(i<0 ||i>=input.size || j<0 || j>=input[0].length) return 0
        if(input[i][j]=='9') return 0
        if(visited.contains(Pair(i,j))) return 0

        visited.add(Pair(i,j))


        val a = if(isHigh(i,j,i-1,j,input)) dfs(input,i-1,j,visited)  else 0
        val b = if(isHigh(i,j,i+1,j,input)) dfs(input,i+1,j,visited)  else 0
        val c = if(isHigh(i,j,i,j-1,input)) dfs(input,i,j-1,visited)  else 0
        val d = if(isHigh(i,j,i,j+1,input)) dfs(input,i,j+1,visited)  else 0
        return 1+a+b+c+d

    }

    fun part2(input: List<String>): Int {
        val basins: ArrayList<Int> = arrayListOf()
        val visited:MutableSet<Pair<Int,Int>> = mutableSetOf()
        for(i in input.indices)
        {
            for(j in input[0].indices)
            {
                if(isLow(i,j,input))
                {
                    visited.clear()
                     basins.add(dfs(input,i,j,visited))
                }
            }
        }

        basins.sortDescending()
        var ans=1
        for(i in 0..2)
        {
            ans*=basins[i]
        }

        return ans
    }


    val testInput = readInput("Day9/Day09_test")
    val input = readInput("Day9/Day09")

    println(part2(testInput))
    println(part2(input))
}