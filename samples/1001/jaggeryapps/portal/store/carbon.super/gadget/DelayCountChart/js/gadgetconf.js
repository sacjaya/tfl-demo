var gadgetConfig ={"id":"DelayCountChart","title":"DelayCountChart","datasource":"delaysCountStream:1.0.0","type":"realtime","columns":[{"name":"TIMESTAMP","type":"time"},{"name":"line","type":"string"},{"name":"delays","type":"int"},{"name":"timeStamp","type":"time"}],"maxUpdateValue":10,"chartConfig":{"x" : "timeStamp","maxLength": 500,  "charts" : [{"type": "line", "color":"line", "y" : "delays"}], "width": 400, "height": 150 },"domain":"carbon.super"};