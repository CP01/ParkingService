# ParkingService

Features Supported:<br>
--------------------------------------<br>
Elder - Lower Rack Preference<br>
Royal - Vacant Slots both sides<br>
Filling starts from closer to Exit<br>
Incentives for Ride Sharing<br>
Incentives for Frequent User
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

