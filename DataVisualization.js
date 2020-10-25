google.charts.load("current", { packages: ["corechart", "bar"] });
// google.charts.setOnLoadCallback(drawStacked);

//Bind click event to make an ajax call to post request of DataVisualization. This will return
// a json object with top 3 review for each city;
$("#btnGetChartData").click(function () {
  $("#btnGetChartData").hide();
  $.ajax({
    url: "Inventory",
    type: "POST",
    data: "{}",
    success: function (msg) {
      createDataTable(msg);
    },
    error: function () {
      console.log("error occurred while making ajax call;");
    },
  });
});

$("#btnGetChartData2").click(function () {
  $("#btnGetChartData").hide();
  $.ajax({
    url: "Sales",
    type: "POST",
    data: "{}",
    success: function (msg) {
      createDataTable2(msg);
    },
    error: function () {
      console.log("error occurred while making ajax call;");
    },
  });
});

const createDataTable = (jsonData) => {
  var parsedData = $.parseJSON(jsonData);
  var data = new Array();
  var productNameArr = new Array(); // productName
  var stockArr = new Array(); // quantity
  let axes = new Array();

  axes[0] = "Products";
  axes[1] = "Quantity";

  // Create an array of product name and an array of zipcodes
  for (var i = 0; i < parsedData.length; i++) {
    var productName = parsedData[i]["productName"];
    var stock = parsedData[i]["Stock"];

    productNameArr.push(productName);
    stockArr.push(stock);
  }

  data[0] = axes;
  let counter = 0;

  for (let i = 1; i < productNameArr.length; i++) {
    let tempArr = new Array();
    tempArr[0] = productNameArr[counter];
    tempArr[1] = stockArr[counter];
    data[i] = tempArr;
    counter++;
  }

  drawChart(data, productNameArr);
};

const createDataTable2 = (jsonData) => {
  var parsedData = $.parseJSON(jsonData);
  var data = new Array();
  var productNameArr = new Array(); // productName
  var stockArr = new Array(); // quantity
  let axes = new Array();

  axes[0] = "Products";
  axes[1] = "Sales";

  // Create an array of product name and an array of zipcodes
  for (var i = 0; i < parsedData.length; i++) {
    var productName = parsedData[i]["productName"];
    var stock = parsedData[i]["Stock"];

    productNameArr.push(productName);
    stockArr.push(stock);
  }

  data[0] = axes;
  let counter = 0;

  for (let i = 1; i < productNameArr.length; i++) {
    let tempArr = new Array();
    tempArr[0] = productNameArr[counter];
    tempArr[1] = stockArr[counter];
    data[i] = tempArr;
    counter++;
  }

  drawChart2(data, productNameArr);
};

const drawChart = (data, productNameArr) => {
  var productNames = "";
  for (var i = 0; i < productNameArr.length; i++) {
    productNames += productNameArr[i] + ",";
  }

  //Invoke google's built in method to get data table object required by google.
  var chartData = google.visualization.arrayToDataTable(data);

  var options = {
    title: "Available Products Chart",
    width: 750,
    height: 650,
    chart: {
      title: "Available Products Chart",
      subtitle: productNames,
    },
    bars: "horizontal", // Required for Material Bar Charts.
  };

  var chart = new google.visualization.BarChart(
    document.getElementById("chart_div")
  );
  chart.draw(chartData, options);
};

const drawChart2 = (data, productNameArr) => {
  var productNames = "";
  for (var i = 0; i < productNameArr.length; i++) {
    productNames += productNameArr[i] + ",";
  }

  //Invoke google's built in method to get data table object required by google.
  var chartData = google.visualization.arrayToDataTable(data);

  var options = {
    title: "Sales Chart",
    width: 750,
    height: 650,
    chart: {
      title: "Sales Chart",
      subtitle: productNames,
    },
    bars: "horizontal", // Required for Material Bar Charts.
  };

  var chart = new google.visualization.BarChart(
    document.getElementById("chart_div")
  );
  chart.draw(chartData, options);
};
