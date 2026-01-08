package routes

import (
	"github.com/gin-gonic/gin"
	"go-task-service/controllers"
)

func TaskRoutes(router *gin.Engine) {
	taskController := controllers.NewTaskController()

	tasks := router.Group("/tasks/v1")
	{
		tasks.GET("", taskController.GetAllTasks)
		tasks.GET("/:id", taskController.GetTaskByID)
		tasks.POST("", taskController.CreateTask)
		tasks.PUT("/:id", taskController.UpdateTask)
		tasks.DELETE("/:id", taskController.DeleteTask)
		tasks.POST("/:taskId/comments", taskController.AddComment)
	}
}