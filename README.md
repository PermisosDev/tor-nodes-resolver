<img src="https://github.com/sammwyy/tor-nodes-resolver/blob/master/assets/icon.png" width="128px" align="left"><h3>Tor Nodes Resolver</h3>
<p>Resolve or check if an IP Address is a tor exit/node</p>
<p>
    <img src="https://raw.githubusercontent.com/MikeCodesDotNET/ColoredBadges/master/svg/dev/languages/java.svg"> 
    <img src="https://raw.githubusercontent.com/MikeCodesDotNET/ColoredBadges/master/svg/dev/languages/go.svg"> 
</p>

<hr>

### What is it?
This project aims to detect TOR nodes to discover if the request comes from here.

### How does this work?
The project internally downloads a list of known nodes from [this file](https://check.torproject.org/torbulkexitlist), it can also make an HTTP request to query using an API.

### Index
- [For Java](https://github.com/permisosdev/tor-nodes-resolver/tree/master/java)
- [For Go](https://github.com/permisosdev/tor-nodes-resolver/tree/golang)
### How to use
#### Java
```java
import dev._2lstudios.tor_nodes_resolver.TorNodesResolver;

public class Test {

    public static void main (String[] args) {
        TorNodesResolver tnr = new TorNodesResolver();

        System.out.println(tnr.isNode("45.154.35.252"));
        System.out.println(tnr.isNode("11.22.33.44"));
    }   

}
```
#### Golang
```go
package main

import (
	"fmt"
	"tornoderesolver/lib"
)

func main() {
	if(lib.IsNode("171.25.193.77")){
		fmt.Printf("is node");
	}else{
		fmt.Printf("not is node")
	}
	
}
```
