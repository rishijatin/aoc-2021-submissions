package Day10

import readInput
import java.util.*
import kotlin.collections.ArrayList


fun main() {


    fun addVal(item:Char):Int {
        return when(item)
        {
            ')' -> 3
            ']' -> 57
            '}' -> 1197
            '>' -> 25137
            else -> 0

        }
    }

    fun revValue(item:Char):Char{
        return when(item)
        {
            ')' -> '('
            ']' -> '['
            '}' -> '{'
            '>' -> '<'
            else -> '.'

        }
    }

    fun part1(input: List<String>): Int {
        var ans=0



        for(item in input)
        {   val st:ArrayList<Char> = arrayListOf()

            for(j in item.indices)
            {
                if(item[j]=='[' || item[j]=='(' || item[j]=='{' || item[j]=='<')
                {
                    st.add(item[j]);
                }
                else
                {
                    if(st.isEmpty())
                    {
                        ans+=addVal(item[j])

                        break
                    }
                    else
                    {
                        if(st[st.size-1]!=revValue(item[j]))
                        {
                            ans+=addVal(item[j])
                            break
                        }
                        else
                        {
                            st.removeLast()
                        }
                    }
                }
            }


        }

        return ans
    }


    val testInput = readInput("Day10/Day10_test")
    val input = readInput("Day10/Day10")

    println(part1(testInput))
    println(part1(input))
}