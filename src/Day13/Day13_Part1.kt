package Day13

import readInput
import java.util.Collections.max
import kotlin.math.max


fun main() {

    fun createMatrix(matrix:ArrayList<ArrayList<Int>>,rows:Int,cols:Int)
    {
        for(i in 0..rows)
        {
            matrix.add(arrayListOf())
            for(j in 0..cols)
            {
                matrix[i].add(0)
            }
        }
    }

    fun reverseMatrixByRow(matrix:ArrayList<ArrayList<Int>>,rows:Int,cols:Int)
    {

    }

    fun reverseMatrixByCol(matrix:ArrayList<ArrayList<Int>>,rows:Int,cols:Int)
    {

    }

    fun divideMatrix(matrix:ArrayList<ArrayList<Int>>,rowStart:Int,rowEnd:Int,colStart:Int,colEnd:Int,axis:String,point:Int)
    :Pair<Pair<Int,Int>,Pair<Int,Int>>
    {
        if(axis=="y")
        {
            if(point-rowStart >= rowEnd-point)
            {
                for(i in point+1..rowEnd)
                {
                    for(j in colStart..colEnd)
                    {
                        if(matrix[i][j]==1)
                        {
                            var axispoint = point-(i-point)
                            matrix[axispoint][j]=1
                        }
                    }
                }

                return Pair(Pair(rowStart,point-1),Pair(colStart,colEnd))

            }
            else
            {
                for(i in rowStart..point)
                {
                    for(j in colStart..colEnd)
                    {
                        if(matrix[i][j]==1)
                        {
                            var axispoint = point+(point-i)
                            matrix[axispoint][j]=1
                        }
                    }
                }

                return Pair(Pair(point+1,rowEnd),Pair(colStart,colEnd))
            }
        }
        else{

            if(point-colStart >= colEnd-point)
            {

                for(i in point+1..colEnd)
                {
                    for(j in rowStart..rowEnd)
                    {
                        if(matrix[j][i]==1)
                        {
                            var axispoint = point-(i-point)
                            matrix[j][axispoint]=1
                        }
                    }
                }

                return Pair(Pair(rowStart,rowEnd),Pair(colStart,point-1))

            }
            else
            {
                for(i in rowStart..point)
                {
                    for(j in colStart..colEnd)
                    {
                        if(matrix[j][i]==1)
                        {
                            var axispoint = point+(point-i)
                            matrix[j][axispoint]=1
                        }
                    }
                }
                return Pair(Pair(rowStart,rowEnd),Pair(point+1,colEnd))
            }
        }
    }

    fun getMaxes(input:List<String>):Pair<Int,Int>
    {
        val maxes:MutableList<Int> = mutableListOf(0,0)
        for(item in input)
        {
            if(item.length==0)
            {
                break
            }

                var(x,y) = item.split(",")
                x=x.trim()
                y=y.trim()
                maxes[0] = max(maxes[0],x.toInt())
                maxes[1] = max(maxes[1],y.toInt())
        }

        return Pair(maxes[0],maxes[1])

    }


    fun part1(input: List<String>): Int {
        var ans= 0

        val maxxes:Pair<Int,Int> = getMaxes(input)

        val matrix:ArrayList<ArrayList<Int>> = arrayListOf()
        createMatrix(matrix,maxxes.second,maxxes.first)

        val foldInstructions:ArrayList<Pair<String,Int>> = arrayListOf()

        var point= true

        for(item in input)
        {
            if(item.length==0)
            {
                point=false
                continue
            }
            if(point)
            {
                var(x,y) = item.split(",")
                x=x.trim()
                y=y.trim()
                matrix[y.toInt()][x.toInt()]=1
            }
            else{
                var(a,b) = item.substring(11).split("=")
                a = a.trim()
                b= b.trim()
                foldInstructions.add(Pair(a,b.toInt()))
            }
        }


        val myPair = divideMatrix(matrix,0,matrix.size-1,0,matrix[0].size-1,
            foldInstructions[0].first,foldInstructions[0].second)



        for(i in myPair.first.first .. myPair.first.second)
        {
            for (j in myPair.second.first..myPair.second.second)
            {
                if(matrix[i][j]==1)
                {
                    ans++
                }
            }
        }

        return ans
    }


    val testInput = readInput("Day13/Day13_test")
    val input = readInput("Day13/Day13")

    println(part1(testInput))
    println(part1(input))
}