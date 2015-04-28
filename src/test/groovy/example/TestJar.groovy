package example

import org.springframework.boot.loader.archive.JarFileArchive
import spock.lang.Specification
import spock.lang.Unroll

class TestJar extends Specification {
	@Unroll
	def "can get manifest from jar (#fileName) of #type"() {
		given:
		def zipjar = new File(fileName)
		when:
		def jarFile = new JarFileArchive(zipjar)
		then:
		jarFile.manifest
		jarFile.mainClass == "Example"
		where:
		fileName | type
		System.properties.TEST_65K_ZIP64 | ">65k files zip64=true"
		System.properties.TEST_ZIP       | "<65k files zip64=false"
		System.properties.TEST_ZIP64     | "<65k files zip64=true"
	}	
}
