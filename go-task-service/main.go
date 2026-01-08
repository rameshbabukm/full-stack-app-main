package main

import (
	"os"

	"github.com/gin-gonic/gin"
	"github.com/gin-contrib/cors"
	"go-task-service/database"
	"go-task-service/routes"
)

func main() {
	// Set default environment variables if not set
	if os.Getenv("DB_HOST") == "" {
		os.Setenv("DB_HOST", "localhost")
	}
	if os.Getenv("DB_USER") == "" {
		os.Setenv("DB_USER", "postgres")
	}
	if os.Getenv("DB_PASSWORD") == "" {
		os.Setenv("DB_PASSWORD", "postgres")
	}
	if os.Getenv("DB_NAME") == "" {
		os.Setenv("DB_NAME", "taskdb")
	}
	if os.Getenv("DB_PORT") == "" {
		os.Setenv("DB_PORT", "5432")
	}

	// Connect to database
	database.ConnectDatabase()

	r := gin.Default()

	// Configure CORS
	config := cors.Config{
		AllowOrigins:     []string{"http://localhost:8000", "http://localhost:3000", "http://127.0.0.1:8000", "http://127.0.0.1:3000"},
		AllowMethods:     []string{"GET", "POST", "PUT", "DELETE", "OPTIONS"},
		AllowHeaders:     []string{"Origin", "Content-Type", "Content-Length", "Accept-Encoding", "X-CSRF-Token", "Authorization", "accept", "origin", "Cache-Control", "X-Requested-With"},
		ExposeHeaders:    []string{"Content-Length"},
		AllowCredentials: true,
		MaxAge:           12 * 3600, // 12 hours
	}
	r.Use(cors.New(config))

	// Setup routes
	routes.TaskRoutes(r)

	r.GET("/api", func(c *gin.Context) {
		c.JSON(200, gin.H{"message": "Hello from Golang!"})
	})

	r.Run(":8081")
}