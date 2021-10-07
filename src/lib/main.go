package lib

import (
	"io/ioutil"
	"net/http"
	"sort"
	"strings"
)


func IsNode(iptocheck string) bool{
	if(sort.SearchStrings(getNodes(), iptocheck) > 0) { 
		return true;
	}else{ 
		return false;
	}
}

func getNodes() []string {
	client := http.Client{};
	req, err := http.NewRequest("GET", "https://check.torproject.org/torbulkexitlist", nil)
	res, err := client.Do(req);
	body := res;

	if err != nil {
		panic(err)
	}

	nodes := strings.Split(reqtostring(body), "\n");
	return nodes;
}

func reqtostring(reqobj *http.Response) string {
	bytearr, err := ioutil.ReadAll(reqobj.Body)
	if err != nil {
		panic(err)
	}
	return string(bytearr); 
}