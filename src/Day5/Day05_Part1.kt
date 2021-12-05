package Day5

import readInput
fun main() {

    fun part1(input: List<String>): Int {

        val matrix = Array(1000){IntArray(1000)}

        for(item in input)
        {
            var (start,stop) = item.split("->")
            start=start.trim()
            stop=stop.trim()

            var (x1,y1) = start.split(",")
            var (x2,y2) = stop.split(",")


            //println("$x1 , $y1 , $x2 , $y2")
            if(x1!=x2 && y1!=y2)
            {

                continue
            }

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

            if(y1==y2)
            {
                if(x1.toInt()>x2.toInt())
                {
                    x1 = x2.also { x2=x1 }
                }

                for(j in x1.toInt()..x2.toInt()) {
                    matrix[j][y1.toInt()]++
                }
            }
        }

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

    println(part1(testInput))
    println(part1(input))
}