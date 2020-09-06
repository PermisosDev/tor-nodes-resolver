const https = require('https');

class TorNodesResolver {
    constructor () {
        let self = this;

        (async function() {
            let url = "https://check.torproject.org/torbulkexitlist";
            let content = await downloadFile(url).catch((e) => {
                throw e;
            });

            self.list = content.split("\n");
        })();
    }

    isReady () {
        return this.list != null;
    }

    isNode (ip) {
        if (this.list == null) {
            return {
                error: "TNR Module is not ready yet! Reason: Downloading nodes cache database..."
            }
        } else {
            return {
                error: null, ip, isTorNode: this.list.includes(ip)
            }
        }
    }  
}

function downloadFile (url) {
    return new Promise ((resolve, reject) => {
        https.get(url, (resp) => {
            let data = '';
    
            resp.on('data', (chunk) => {
                data += chunk;
            });
    
            resp.on('end', () => {
                resolve(data);
            });
        }).on("error", (err) => {
            reject(error);
        });
    })
}

module.exports = () => {
    return new TorNodesResolver();
}
