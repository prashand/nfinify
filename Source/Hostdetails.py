import wmi

class Hostdetails:
    cpu = 0
    ram = 0
    mac = ""

    def __init__(self):
        self.cpu = self.WMIQuery("Select * From Win32_Processor").LoadPercentage
        self.ram = self.WMIQuery("Select * From Win32_OperatingSystem").FreePhysicalMemory
        self.mac = self.WMIQuery("Select * FROM Win32_NetworkAdapterConfiguration where IPEnabled = True").MACAddress
    def WMIQuery(self,wql):
        c = wmi.WMI()
        for item in c.query(wql):
            return item
