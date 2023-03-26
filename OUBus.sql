Use oubus;

CREATE TABLE Customer (
	customerID nvarchar(50) primary key,
    name nvarchar(50),
    address nvarchar(50),
    email nvarchar(50),
    phoneNumber nvarchar(50)
);

CREATE TABLE Employee(
	employeeID nvarchar(50) primary key,
    name nvarchar(50),
    sex boolean,
    DateOfBirth datetime,
    nationality nvarchar(50),
    nationalID nvarchar(50),
    address nvarchar(50),
    email nvarchar(50),
    phoneNumber nvarchar(50),
    position nvarchar(50)
);

CREATE TABLE Bus (
	busID int primary key auto_increment,
    vehicleName nvarchar(50),
    manufacturer nvarchar(50),
    licensePlate nvarchar(50),
    totalSeat nvarchar(50),
    busType nvarchar(50)
);

CREATE TABLE Location(
	locationID int primary key auto_increment,
    name nvarchar(50)
);

CREATE TABLE Trip (
	tripID int primary key auto_increment,
    busID int,
    departure int,
<<<<<<< Updated upstream
    TimeOfDeparture nvarchar(50),
    DateOfDeparture nvarchar(50),
=======
    TimeOfDeparture Time(1),
    DateOfDeparture Date,
>>>>>>> Stashed changes
    destination int,
    FOREIGN KEY (busID) REFERENCES Bus(busID),
    FOREIGN KEY (departure) REFERENCES Location(locationID),
    FOREIGN KEY (destination) REFERENCES Location(locationID)
);

CREATE TABLE BILL (
	billID nvarchar(50) primary key,
    customerID nvarchar(50),
    employeeID nvarchar(50),
    tripID int,
    seatNo nvarchar(50),
    state int,
    totalDue Double,
    aquiredDate datetime,
    FOREIGN KEY (customerID) REFERENCES Customer(customerID),
    FOREIGN KEY (employeeID) REFERENCES Employee(employeeID),
    FOREIGN KEY (tripID) REFERENCES Trip(tripID)
);

CREATE TABLE Job(
	assignedDate datetime primary key,
    employeeID nvarchar(50),
    tripID int,
    jobDescription nvarchar(200),
    jobState int,
    FOREIGN KEY (employeeID) REFERENCES Employee(employeeID),
    FOREIGN KEY (tripID) REFERENCES Trip(tripID)
);

CREATE TABLE Accounts(
	accountID nvarchar(50) primary key,
    employeeID nvarchar(50),
    username nvarchar(50),
	password nvarchar(50),
    accessedLevel int,
    FOREIGN KEY (employeeID) REFERENCES Employee(employeeID)
);

CREATE TABLE RuleSet(
	ticketBookingTimeLimit nvarchar(50),
    ticketPurcharsingTimeLimit nvarchar(50),
    ticketRedeemingTimeLimit nvarchar(50),
    ticketSwapingTimeLimit nvarchar(50),
    ticketIntertionTimeLimit nvarchar(50)
);