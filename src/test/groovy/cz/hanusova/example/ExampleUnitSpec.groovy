package cz.hanusova.example

import spock.lang.Specification


class ExampleUnitSpec extends Specification {

    def setupSpec() {
        println("Setup before all tests")
    }

    def setup() {
        println("Single test setup")
    }

    def cleanup() {
        println("Single test cleanup")
    }

    def cleanupSpec() {
        println("Cleanup after all tests")
    }

    def 'Simple when/then test'() {
        given: 'Prerequisites'
        def a = 1
        def b = 2

        when: 'Stimulus'
        def c = a + b

        then: 'Response check'
        c == 3
    }

    def 'Simple expect test'() {
        given: 'Prerequisites'
        def a = 1
        def b = 2

        expect: 'Stimulus + response check'
        a + b == 3
    }

    def 'Parametrized test: #a + #b == #c'() {
        expect:
        a + b == c

        where:
        a  | b  || c
        1  | 2  || 3
        2  | 3  || 5
        1  | 1  || 2
        -3 | 3  || 0
        -5 | -5 || -10
    }

    def 'ArrayList accepts null values'() {
        given:
        def list = new ArrayList()

        when:
        list.add(null)

        then:
        notThrown(NullPointerException)
    }

    def 'Array check' () {
        given:
        def array = [1, 2, 3]

        when:
        array.addAll([4, 5, 6])

        then:
        array.size() == 6
        array.every({it > 0})
    }


}