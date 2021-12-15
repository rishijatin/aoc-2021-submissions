package Day15

import readInput
import java.lang.Integer.min
import javax.swing.text.html.HTML.Tag.P


fun main() {

    fun init_distance(distance:ArrayList<ArrayList<Int>>,rows:Int,cols:Int)
    {
        for(i in 0 until rows)
        {
            distance.add(arrayListOf())
            for(j in 0 until cols)
            {
                distance[i].add(Int.MAX_VALUE)
            }
        }
    }

    fun min_distance(distance:ArrayList<ArrayList<Int>>,visited:MutableSet<Pair<Int,Int>>):Pair<Int,Int>{
        var mini = Int.MAX_VALUE
        var pair:Pair<Int,Int> = Pair(0,0)

        for(i in 0 until distance.size){
            for(j in 0 until  distance[0].size){
                if(!visited.contains(Pair(i,j)) && distance[i][j]<=mini)
                {
                    mini = distance[i][j]
                    pair = Pair(i,j)
                }
            }
        }

        return pair
    }

    fun isValid(i:Int,j:Int,rows:Int,cols:Int):Boolean{
        if(i<0 || i>=rows || j<0 || j>=cols) return false
        return true
    }

    fun util(curr:Pair<Int,Int>,distance:ArrayList<ArrayList<Int>>,matrix:ArrayList<ArrayList<Int>>,
             visited:MutableSet<Pair<Int,Int>>,i:Int,j:Int)
    {
        if(isValid(curr.first+i,curr.second+j,matrix.size,matrix[0].size)
            &&!visited.contains(Pair(curr.first+i,curr.second+j))
            && distance[curr.first][curr.second]!=Int.MAX_VALUE
            && (distance[curr.first][curr.second] + matrix[curr.first+i][curr.second+j]) < distance[curr.first+i][curr.second+j]
        )
        {
            distance[curr.first+i][curr.second+j] = distance[curr.first][curr.second] + matrix[curr.first+i][curr.second+j]
        }
    }

    fun shortestPath(matrix:ArrayList<ArrayList<Int>>,src:Pair<Int,Int>):Int{

        val visited:MutableSet<Pair<Int,Int>> = mutableSetOf()

        val distance:ArrayList<ArrayList<Int>> = arrayListOf()

        init_distance(distance,matrix.size,matrix[0].size)

        distance[src.first][src.second]=0

        for(i in 1 until matrix.size*matrix[0].size)
        {
            val curr = min_distance(distance,visited)
            visited.add(curr)
            util(curr,distance,matrix,visited,1,0)
            util(curr,distance,matrix,visited,-1,0)
            util(curr,distance,matrix,visited,0,1)
            util(curr,distance,matrix,visited,0,-1)
        }


        return distance[matrix.size-1][matrix[0].size-1]
    }

    fun part1(input: List<String>): Int {

        val matrix:ArrayList<ArrayList<Int>> = arrayListOf()

        for(item in input)
        {
            matrix.add(arrayListOf())
            for(number in item.indices)
            {
                matrix[matrix.size-1].add(item[number]-'0')
            }
        }

        return shortestPath(matrix,Pair(0,0))
    }

    val testInput = readInput("Day15/Day15_test")
    val input = readInput("Day15/Day15")

    println(part1(testInput))
    println(part1(input))
}