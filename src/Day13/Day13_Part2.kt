package Day13

import readInput
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

    fun reverseMatrixByRow(matrix:ArrayList<ArrayList<Int>>,rowStart:Int,rowEnd:Int,colStart:Int,colEnd:Int)
    {
        var _rowStart = rowStart
        var _rowEnd = rowEnd

        while(_rowStart!=_rowEnd)
        {
            for(i in colStart..colEnd)
            {
                val temp = matrix[_rowStart][i]
                matrix[_rowStart][i] =matrix[_rowEnd][i]
                matrix[_rowEnd][i]=temp
            }
            _rowStart++
            _rowEnd--
        }
    }

    fun reverseMatrixByCol(matrix:ArrayList<ArrayList<Int>>,rowStart:Int,rowEnd:Int,colStart:Int,colEnd:Int)
    {
        var _colStart = colStart
        var _colEnd = colEnd

        while(_colStart!=_colEnd)
        {
            for(i in rowStart..rowEnd)
            {
                val temp = matrix[i][_colStart]
                matrix[i][_colStart] =matrix[i][_colEnd]
                matrix[i][_colEnd]=temp
            }
            _colStart++
            _colEnd--
        }
    }

    fun shiftRowsUp(matrix:ArrayList<ArrayList<Int>>,points:Int)
    {
        for(i in points until matrix.size)
        {
            for(j in 0 until matrix[0].size)
            {
                matrix[i-points][j]=matrix[i][j]
            }
        }
    }

    fun shiftRowsLeft(matrix:ArrayList<ArrayList<Int>>,points:Int)
    {
        for(i in points until matrix[0].size)
        {
            for(j in 0 until matrix.size)
            {
                matrix[j][i-points]=matrix[j][i]
            }
        }
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
                reverseMatrixByRow(matrix,point+1,rowEnd,colStart,colEnd)
                shiftRowsUp(matrix,point+1)
                return Pair(Pair(rowStart,rowEnd-point),Pair(colStart,colEnd))
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
                reverseMatrixByCol(matrix,rowStart,rowEnd,point+1,colEnd)
                shiftRowsLeft(matrix,point+1)
                return Pair(Pair(rowStart,rowEnd),Pair(colStart,colEnd-point))
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

    fun part2(input: List<String>): Int {
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

        var temp = Pair(Pair(0,matrix.size-1),Pair(0,matrix[0].size-1))

        for(item in foldInstructions)
        {
            temp = divideMatrix(matrix,temp.first.first,temp.first.second,temp.second.first,
                temp.second.second,item.first,item.second)
        }



        for(i in temp.first.first .. temp.first.second)
        {
            for (j in temp.second.first..temp.second.second)
            {
                if(matrix[i][j]==1)
                {
                    print("#")
                    ans++
                }
                else{
                    print(" ")
                }
            }
            println()
        }

        return ans
    }


    val testInput = readInput("Day13/Day13_test")
    val input = readInput("Day13/Day13")

    println(part2(testInput))
    println(part2(input))
}