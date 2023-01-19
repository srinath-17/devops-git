case $1 in 
	start)
		echo "server is starting"
		sh createlog.sh ;;
	stop)
		echo "server is stopping"
		sh -x createlog.sh
		echo "server is stopped"
esac
