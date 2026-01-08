package models

import (
	"time"

	"gorm.io/gorm"
)

type Task struct {
	ID                   uint           `json:"id" gorm:"primaryKey"`
	Number               string         `json:"number" gorm:"unique;not null"`
	Title                string         `json:"title" gorm:"not null"`
	Status               string         `json:"status" gorm:"not null"`
	Assignee             *string        `json:"assignee"`
	CreatedAt            time.Time      `json:"created_at"`
	DeletedAt            gorm.DeletedAt `json:"deleted_at" gorm:"index"`
	Description          *string        `json:"description"`
	DescriptionRichText  *string        `json:"description_rich_text"`
	DueDate              *time.Time     `json:"due_date"`
	UpdatedAt            time.Time      `json:"updated_at"`
	Priority             string         `json:"priority" gorm:"not null"`
	Comments             []Comment      `json:"comments" gorm:"foreignKey:TaskItemID"`
}

type Comment struct {
	ID         uint      `json:"id" gorm:"primaryKey"`
	TaskItemID uint      `json:"task_item_id"`
	CreatedAt  time.Time `json:"created_at"`
	UpdatedAt  time.Time `json:"updated_at"`
	UserName   *string   `json:"user_name"`
	Comment    string    `json:"comment" gorm:"type:text"`
}