package test

import (
	"math"
	"os"
	"testing"
	"text/template"
)

type HpaConfig struct {
	Busi3Id        int
	ProjectId      int
	ProjectName    string
	Version        string
	Namespace      string
	Replica        int
	DeploymentName string
}

const yamlTemplate = `apiVersion: autoscaling/v1
kind: HorizontalPodAutoscaler
metadata:
  labels:
    busi3Id: '{{.Busi3Id}}'
    creator: k8smanager
    project-id: '{{.ProjectId}}'
    project-name: '{{.ProjectName}}'
    release: '{{.Version}}'
  name: '{{.ProjectName}}'
  namespace: '{{.Namespace}}'
spec:
  maxReplicas: {{ceil (mul 1.5 .Replica)}}
  minReplicas: {{.Replica}}
  scaleTargetRef:
    apiVersion: apps/v1
    kind: Deployment
    name: '{{.DeploymentName}}'
  targetCPUUtilizationPercentage: 150
  behavior:
    scaleDown:
      policies:
      - periodSeconds: 60
        type: Percent
        value: 30
      selectPolicy: Max
      stabilizationWindowSeconds: 300
    scaleUp:
      policies:
      - periodSeconds: 60
        type: Percent
        value: 30
      selectPolicy: Max
      stabilizationWindowSeconds: 180
`

func TestTemplate(t *testing.T) {
	funcMap := template.FuncMap{
		"mul": func(a float64, b int) float64 {
			return a * float64(b)
		},
		"ceil": func(a float64) int {
			return int(math.Ceil(a))
		},
	}

	tmpl := template.Must(template.New("hpa").Funcs(funcMap).Parse(yamlTemplate))

	config := HpaConfig{
		Busi3Id:        123,
		ProjectId:      456,
		ProjectName:    "my-service",
		Version:        "v1.2.3",
		Namespace:      "production",
		Replica:        3,
		DeploymentName: "my-service-deploy",
	}

	tmpl.Execute(os.Stdout, config)
}
