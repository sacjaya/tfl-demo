var gadgetConfig ={"id":"BusDelayCount","title":"BusDelayCount","datasource":"delaysCountStream:1.0.0","type":"realtime","columns":[{"name":"TIMESTAMP","type":"time"},{"name":"line","type":"string"},{"name":"delays","type":"int"},{"name":"timeStamp","type":"long"}],"maxUpdateValue":10,"chartConfig":{"x" : "line", "charts" : [{"type": "bar",  "y" : "delays"}], "width": 200, "height": 150 },"domain":"carbon.super"};