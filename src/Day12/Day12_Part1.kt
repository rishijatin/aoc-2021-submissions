package Day12

import readInput


fun main() {

    fun getTotalNodes(input:List<String>):Int{
        val mySet: MutableSet<String> = mutableSetOf()

        for(item in input)
        {
            val (a,b) = item.split("-")
            mySet.add(a)
            mySet.add(b)
        }
        return mySet.size
    }

    fun dfs(graph:MutableMap<String,ArrayList<String>>,curr:String,ans:MutableList<Int>,visited:MutableSet<String>)
    {
        if(curr=="end"){
            ans[0]=ans[0]+1
            return
        }

        if(visited.contains(curr)) return
        if(curr[0] in 'a'..'z')
        {
            visited.add(curr)
        }

        for(item in graph[curr]!!)
        {
            dfs(graph,item,ans,visited)
        }
        visited.remove(curr)

    }

    fun part1(input: List<String>): Int {
        var ans= mutableListOf<Int>(0)

        val graph: MutableMap<String,ArrayList<String>> = mutableMapOf()

        for(item in input)
        {
            val(a,b) = item.split("-")

            if(graph.containsKey(a))
            {
                graph[a]?.add(b)
            }
            else{
                graph[a] = arrayListOf(b)
            }

            if(graph.containsKey(b))
            {
                graph[b]?.add(a)
            }
            else{
                graph[b] = arrayListOf(a)
            }
        }

        val visited: MutableSet<String> = mutableSetOf()

        dfs(graph,"start",ans,visited)

        return ans[0]
    }


    val testInput = readInput("Day12/Day12_test")
    val input = readInput("Day12/Day12")

    println(part1(testInput))
    println(part1(input))
}