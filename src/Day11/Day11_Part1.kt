package Day11

import readInput
import kotlin.collections.ArrayList


fun main() {

    val X= listOf<Int>(1,-1,0,0,1,-1,1,-1)
    val Y = listOf<Int>(0,0,1,-1,1,-1,-1,1)

    fun isValid(i:Int,j:Int,_i:Int,_j:Int):Boolean
    {
        if(i<0 || i>=_i || j<0 || j>=_j) return false
        return true
    }

    fun chainReaction(matrix:ArrayList<ArrayList<Int>>,i:Int,j:Int,visited:MutableSet<Pair<Int,Int>>):Int{


        visited.add(Pair(i,j))

        var ans=1

        matrix[i][j]=0

        for(idx in X.indices)
        {
            if(isValid(i+X[idx],j+Y[idx],matrix.size,matrix[0].size) && !visited.contains(Pair(i+X[idx],j+Y[idx])))
            {

                matrix[i+X[idx]][j+Y[idx]]++
                if(matrix[i+X[idx]][j+Y[idx]]>9)
                {
                    ans+=chainReaction(matrix,i+X[idx],j+Y[idx],visited)
                }
            }
        }


        return ans

    }

    fun formatMatrix(matrix:ArrayList<ArrayList<Int>>){
        for(i in matrix.indices)
        {
            for(j in matrix[0].indices)
            {
                print(matrix[i][j])
            }
            println()
        }
    }


    fun doStep(matrix:ArrayList<ArrayList<Int>>,visited:MutableSet<Pair<Int,Int>>):Int
    {

        var ans=0

        val mySet:MutableSet<Pair<Int,Int>> = mutableSetOf()

        for(i in matrix.indices)
        {
            for(j in matrix[0].indices)
            {
                if(matrix[i][j]==9)
                {
                    matrix[i][j]=0
                    visited.add(Pair(i,j))
                    mySet.add(Pair(i,j))
                }
                else
                {
                    matrix[i][j]++
                }
            }
        }

        for(i in matrix.indices)
        {
            for(j in matrix[0].indices)
            {
                if(matrix[i][j]==0 && mySet.contains(Pair(i,j)))
                {
                    ans+=chainReaction(matrix,i,j,visited)
                }
            }
        }

        return ans
    }

    fun part1(input: List<String>): Int {
        var ans=0

        val matrix:ArrayList<ArrayList<Int>> = arrayListOf()

        for(item in input)
        {
            val tempList = arrayListOf<Int>()
            for(j in item.indices)
            {
                tempList.add(item[j]-'0')
            }
            matrix.add(tempList)
        }

        val visited:MutableSet<Pair<Int,Int>> = mutableSetOf()


        repeat(100)
        {
            visited.clear()
            ans+=doStep(matrix,visited)
            //formatMatrix(matrix)
            //println()
        }
        return ans
    }


    val testInput = readInput("Day11/Day11_test")
    val input = readInput("Day11/Day11")

    println(part1(testInput))
    println(part1(input))
}