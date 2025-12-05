<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Website Dashboard</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" href="css/dashboard.css">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    
</head>
<body>
    <!-- Sidebar -->
    <div class="sidebar">
        <div class="sidebar-header">
            <i class="fas fa-chart-line fa-2x"></i>
            <h2>Dashboard</h2>
        </div>
        <div class="sidebar-menu">
            <ul>
                <li class="active">
                    <a href="#">
                        <i class="fas fa-home"></i>
                        <span>Dashboard</span>
                    </a>
                </li>


		<li>
                    <a href="#">
                        <i class="fas fa-home"></i>
                        <span>Submit Forms</span>
                    </a>
                </li>

		<li>
                    <a href="#">
                        <i class="fas fa-home"></i>
                        <span>Veiw Submitted Forms</span>
                    </a>
                </li>

		<li>
                    <a href="#">
                        <i class="fas fa-home"></i>
                        <span>Verification Status</span>
                    </a>
                </li>
		<!--
                <li>
                    <a href="#">
                        <i class="fas fa-users"></i>
                        <span>Users</span>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <i class="fas fa-shopping-cart"></i>
                        <span>Products</span>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <i class="fas fa-chart-bar"></i>
                        <span>Analytics</span>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <i class="fas fa-cog"></i>
                        <span>Settings</span>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <i class="fas fa-question-circle"></i>
                        <span>Help</span>
                    </a>
                </li>
		--->
            </ul>
        </div>
    </div>

    <!-- Main Content -->
    <div class="main-content">
        <!-- Header -->
        <div class="header">
            <h1>Dashboard Overview</h1>
            <div class="user-info">
                <img src="images/headshot_1.webp" alt="User Avatar"> <!-- TODO:Change this to a stock image --->
                <span>Anonymous</span> <!-- TODO:Make this the user's first, and last name --->
            </div>
        </div>

        <!-- Cards Grid -->
        <div class="cards-grid">
            <div class="card">
                <div class="card-header">
                    <div class="card-title">Verification Status</div>
                    <div class="card-icon users">
                        <i class="fas fa-users"></i>
                    </div>
                </div>
                <div class="card-value">Not yet Verified </div> <!-- TODO:Populate with the verification status --->
            </div>

            <div class="card">
                <div class="card-header">
                    <div class="card-title">Verification Forms</div>
                    <div class="card-icon views">
                        <i class="fas fa-eye"></i>
                    </div>
                </div>
                <div class="card-value">Not yet Completed</div>
            </div>


           
        </div>

        <!-- Recent Activity -->
        <div class="activity-container">
            <div class="chart-header">
                <div class="chart-title">Recent Activity</div>
                <div class="chart-actions">
                    <a href="#">View All</a>
                </div>
            </div>
	 <!----
            <ul class="activity-list">
                <li class="activity-item">
                    <div class="activity-icon user">
                        <i class="fas fa-user-plus"></i>
                    </div>
                    <div class="activity-details">
                        <h4>New user registered</h4>
                        <p>John Smith joined the platform</p>
                    </div>
                    <div class="activity-time">2 min ago</div>
                </li>
                <li class="activity-item">
                    <div class="activity-icon payment">
                        <i class="fas fa-credit-card"></i>
                    </div>
                    <div class="activity-details">
                        <h4>Payment received</h4>
                        <p>Order #45892 completed</p>
                    </div>
                    <div class="activity-time">15 min ago</div>
                </li>
                <li class="activity-item">
                    <div class="activity-icon content">
                        <i class="fas fa-file-alt"></i>
                    </div>
                    <div class="activity-details">
                        <h4>New article published</h4>
                        <p>"How to improve your website SEO"</p>
                    </div>
                    <div class="activity-time">1 hour ago</div>
                </li>
                <li class="activity-item">
                    <div class="activity-icon user">
                        <i class="fas fa-user-check"></i>
                    </div>
                    <div class="activity-details">
                        <h4>Profile updated</h4>
                        <p>Sarah Johnson updated her profile</p>
                    </div>
                    <div class="activity-time">3 hours ago</div>
                </li>
            </ul> --->
        </div>
    </div>

    <script>
        // Traffic Chart
        const trafficCtx = document.getElementById('trafficChart').getContext('2d');
        const trafficChart = new Chart(trafficCtx, {
            type: 'line',
            data: {
                labels: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
                datasets: [{
                    label: 'Visitors',
                    data: [3200, 4100, 3800, 5200, 6100, 7500, 8200],
                    borderColor: '#4361ee',
                    backgroundColor: 'rgba(67, 97, 238, 0.1)',
                    borderWidth: 2,
                    fill: true,
                    tension: 0.4
                }]
            },
            options: {
                responsive: true,
                plugins: {
                    legend: {
                        display: false
                    }
                },
                scales: {
                    y: {
                        beginAtZero: true,
                        grid: {
                            drawBorder: false
                        }
                    },
                    x: {
                        grid: {
                            display: false
                        }
                    }
                }
            }
        });

        // User Distribution Chart
        const userCtx = document.getElementById('userChart').getContext('2d');
        const userChart = new Chart(userCtx, {
            type: 'doughnut',
            data: {
                labels: ['Desktop', 'Mobile', 'Tablet'],
                datasets: [{
                    data: [60, 30, 10],
                    backgroundColor: [
                        '#4361ee',
                        '#4cc9f0',
                        '#f72585'
                    ],
                    borderWidth: 0
                }]
            },
            options: {
                responsive: true,
                plugins: {
                    legend: {
                        position: 'bottom'
                    }
                }
            }
        });
    </script>
</body>
</html>