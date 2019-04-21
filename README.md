# ParkingService

Features Supported:<br>
--------------------------------------<br>
Elder - Lower Rack Preference<br>
Royal - Vacant Slots both sides<br>
Filling starts from closer to Exit<br>
Incentives for Ride Sharing<br>
Incentives for Frequent User<br>
We make it easy for customer to remember the slots by using words like Invent_Or, Direct_Or, etc instead of alpha-numeric combinations.<br>
RowNames { INVENT, FORMAT, NARRAT, DIRECT }<br>
ColumnNames { _OR, _ABLE, _IVE, _ING, _ED  }
<br>----------------------------------<br>
Design:<br>
<pre>
Exit<br>
|    00 01 02 03 04 <br>
|___exit__<<< entry<--|<br>
|    10 11 12 13 14   |<br>
|    20 21 22 23 24   |<br>
|-<-exit-----entry-<<<|<br>
     30 31 32 33 34   |<br>
                    Entry</pre>
                    
<br>Assumption :<br>
-Gondor have proper mechanism to easily put and pick Bikes from upper racks<br>

-Payment Channle will use points while billing


<br>----------------------------------<br>
<br>Key points to understand Summary Displayed after adding/removing vehicle:
<br>
Cx -> x Car<br>
Bx -> x Bike<br>
Bottom_Upper Rack Representation<br>
<pre>  C0_C1 -> 0 Car in bottom rack while 1 car in upper rack
</pre><br>
