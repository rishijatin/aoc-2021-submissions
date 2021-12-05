package Day5

import readInput
import kotlin.math.abs

fun main() {

    fun is45(x1: Int, y1: Int, x2: Int, y2: Int): Boolean {

        return abs(x1-x2) == abs(y1-y2)
    }

    fun part2(input: List<String>): Int {
        val matrix = Array(1000){IntArray(1000)}

        for(item in input)
        {
            var (start,stop) = item.split("->")
            start=start.trim()
            stop=stop.trim()

            var (x1,y1) = start.split(",")
            var (x2,y2) = stop.split(",")


            //println("$x1 , $y1 , $x2 , $y2")
            if(x1==x2)
            {
                if(y1.toInt()>y2.toInt())
                {
                    y1 = y2.also { y2=y1 }
                }

                for(j in y1.toInt()..y2.toInt()) {
                    matrix[x1.toInt()][j]++
                }
            }

            else if(y1==y2)
            {
                if(x1.toInt()>x2.toInt())
                {
                    x1 = x2.also { x2=x1 }
                }

                for(j in x1.toInt()..x2.toInt()) {
                    matrix[j][y1.toInt()]++
                }
            }

            else if(is45(x1.toInt(),y1.toInt(),x2.toInt(),y2.toInt()))
            {
                var (_x1,_x2,_y1,_y2) = listOf( x1.toInt(),x2.toInt(),y1.toInt(),y2.toInt())

                while(_x1!=_x2)
                {
                    matrix[_x1][_y1]++;
                    if(_x1<_x2)
                    {
                        _x1++
                    }
                    else{
                        _x1--
                    }
                    if(_y1<_y2)
                    {
                        _y1++
                    }
                    else
                    {
                        _y1--
                    }
                }

                matrix[_x1][_y1]++

            }

        }

//        for(i in 0 until 10)
//        {
//            for(j in 0 until 10)
//            {
//                print("${matrix[i][j]} ")
//            }
//            println()
//        }


        var ans=0
        for(i in 0 until 1000)
        {
            for(j in 0 until 1000)
            {
                if(matrix[i][j]>=2)
                {
                    ans++
                }
            }
        }


        return ans
    }


    val testInput = readInput("Day5/Day05_test")
    val input = readInput("Day5/Day05")

    println(part2(testInput))
    println(part2(input))
}