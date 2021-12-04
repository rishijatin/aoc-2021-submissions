package Day04

import readInput
fun main() {

    fun isValidBingo(matrix:ArrayList<ArrayList<String>>):Boolean
    {
        val rows = matrix.size
        val cols = matrix[0].size

        for(i in 0 until rows)
        {
            var check=true

            for(j in 0 until cols)
            {
                if(matrix[i][j]!="*")
                {
                    check=false
                    break;
                }
            }

            if(check) return true
        }


        for(i in 0 until cols)
        {
            var check=true

            for(j in 0 until rows)
            {
                if(matrix[j][i]!="*")
                {
                    check=false
                    break;
                }
            }

            if(check) return true
        }
//        var check = true
//        for(i in 0 until cols)
//        {
//
//            if(matrix[i][i]!="*") {
//                check = false;
//                break;
//            }
//        }
//        if(check) return true
//
//        check=true
//
//        for(i in 0 until cols)
//        {
//            if(matrix[i][cols-i-1]!="*")
//            {
//                check=false;
//                break
//            }
//        }
//
//        if(check) return true

        return false

    }

    fun getSumNum(matrix:ArrayList<ArrayList<String>>):Int{

        var ans=0

        for(row in matrix)
        {
            for(el in row)
            {
                if(el!="*")
                {
                    ans+=el.toInt()
                }
            }
        }

        return ans

    }

    fun insertIntoBingo(matrix:ArrayList<ArrayList<String>>,seq:String):Boolean
    {
        for(i in matrix.indices)
        {
            for(j in matrix[i].indices)
            {
                if(matrix[i][j]==seq)
                {
                    matrix[i][j]="*";
                }
            }
        }

        return isValidBingo(matrix)

    }

    fun part1(input: List<String>): Int {

        val numSequences = input[0].split(",")
        val bingos : ArrayList<ArrayList<ArrayList<String>>> = arrayListOf()

        for(i in 1 until input.size)
        {
            if(input[i]=="")
            {
                bingos.add(ArrayList<ArrayList<String>>())
                continue
            }
            else
            {
                bingos[bingos.size-1].add(ArrayList<String>())
                val row = bingos[bingos.size-1].size-1

                for(item in input[i].split(" "))
                {   if(item!="")
                    bingos[bingos.size-1][row].add(item)
                }
            }

        }

        var resultantBingo =0
        var resultantItem = ""
        var check=false
        for(item in numSequences)
        {
            for(i in bingos.indices)
            {
                if(insertIntoBingo(bingos[i],item.trim()))
                {   check=true
                    //println(bingos[i])
                    resultantBingo=i
                    resultantItem=item.trim()
                    break
                }
            }
            if(check)break
        }

        var ans=0
        ans = getSumNum(bingos[resultantBingo])
        return ans* resultantItem.toInt()
    }


    val testInput = readInput("Day04/Day04_test")
    val input = readInput("Day04/Day04")

    println(part1(testInput))
    println(part1(input))
}