import edu.sperek.graphlab.GraphModel
import kotlin.Pair
import spock.lang.Specification

class GraphMachineSpec extends Specification {
    def edges
    GraphModel graphModel

    def setup() {
        edges = [new Pair(1, 2), new Pair(2, 3), new Pair(3, 4), new Pair(1, 4), new Pair(2, 4)]
        graphModel = new GraphModel(4, 5, edges as ArrayList<Pair<Integer, Integer>>)
    }

    def 'Should return the number of verticies'() {
        given: "a graph"
        when: "user retrieves number of verts"
        int verts = graphModel.order
        then:
        verts == 4
    }

    def 'Should return list of vertices'() {
        given: "a graph"
        when: "user retrieves list of vertices"
        def vertsList = graphModel.verticesList
        then:
        vertsList == [1, 2, 3, 4]
    }

    def 'Should return the number of edges'() {
        given: "a graph"
        when: "user retrieves for number of edges"
        def numberOfEdges = graphModel.size
        then:
        numberOfEdges == 5
    }

    def 'Should return list of edges'() {
        given: "a graph"
        when: "user retrieves for list of edges"
        def edges = graphModel.edgesList
        then:
        edges == ["1-2", "2-3", "3-4", "1-4", "2-4"]
    }

    def 'Should return an adjacency matrix'() {
        given: "a graph"
        when: "user retrieves for adjacency matrix"
        def adjacencyMatrix = graphModel.adjacencyMatrix
        def expectedMatrix = [
                [0, 1, 0, 1],
                [1, 0, 1, 1],
                [0, 1, 0, 1],
                [1, 1, 1, 0]
        ] as Integer[][]
        then:
        expectedMatrix == adjacencyMatrix
    }

    def 'Should return an incidence matrix'() {
        given: "a graph"
        when: "user retrieves for incidence matrix"
        def incidenceMatrix = graphModel.incidenceMatrix
        def expectedMatrix = [
                [1, 0, 0, 1, 0],
                [1, 1, 0, 0, 1],
                [0, 1, 1, 0, 0],
                [0, 0, 1, 1, 1]
        ] as Integer[][]
        then:
        expectedMatrix == incidenceMatrix
    }

    def 'Should return the list of vertices degrees'() {
        given: "a graph"
        when: "user retrieves for vertices degrees"
        def verticesDegrees = graphModel.verticesDegrees
        def expectedDegrees = [
                new Pair(1, 2),
                new Pair(2, 3),
                new Pair(3, 2),
                new Pair(4, 3)
        ] as List<Pair>
        then:
        expectedDegrees == verticesDegrees
    }

    def 'Should return the sequence of graph degrees'() {
        given: "a graph"
        when: "user retrieves for sequence of graph degrees"
        def graphDegreesSequence = graphModel.degreesSequence
        def expectedDegreesSequence = [
                2, 2, 3, 3
        ] as List<Integer>
        then:
        expectedDegreesSequence == graphDegreesSequence

    }

    def 'Should check if graph is simple' () {
        given: "a graph"
        when: "user checks if graph is simple"
        def isSimple = graphModel.isSimple()
        def expected = true
        then:
        expected == isSimple
    }

    def 'Should return needed edges to make complete graph ' () {
        given: "a graph"
        when: "user checks for edges needed to make complete graph"
        def fulfillingEdges = graphModel.fulfillingEdges
        def expected = [new Pair(1,3)] as List<Pair>
        then:
        fulfillingEdges == expected
    }
}
