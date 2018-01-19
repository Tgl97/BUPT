#include <linux/init.h>
#include <linux/kernel.h>
#include <linux/module.h>
#include <linux/proc_fs.h>
#include <linux/fs.h>
#include <linux/vmalloc.h>
#include <asm/uaccess.h>
#include <linux/seq_file.h>

//Prototypes
static int init_clock(void);
static void cleanup_clock(void);
static ssize_t proc_read_clock(struct file *file,char __user *pszPage,size_t size,loff_t *off);

//#define MODULE
#define MODULE_VERSION	"1.0"
#define USER_ROOT_DIR "clock"
#define MODULE_NAME	"my_clock"

//user defined directory
struct proc_dir_entry *my_clock_dir;
struct proc_dir_entry *my_clock_file;

static const struct file_operations my_clock_fops = 
{
	.owner = THIS_MODULE,
	.read = proc_read_clock,
};

//Functions
static int init_clock(void)
{
    //Create user root dir under /proc
	printk("clock: init_module()\n");
	my_clock_dir= proc_mkdir(USER_ROOT_DIR,NULL);
	my_clock_file = proc_create(MODULE_NAME,0,my_clock_dir, &my_clock_fops);
	printk(KERN_INFO"%s %s has initialized.\n",MODULE_NAME,MODULE_VERSION);
    return 0;
}

static void cleanup_clock(void)
{
    printk("clock: cleanup_module()\n");
	remove_proc_entry(MODULE_NAME,my_clock_dir);
	remove_proc_entry(USER_ROOT_DIR,NULL);
	printk(KERN_INFO"%s %s has removed.\n",MODULE_NAME,MODULE_VERSION);
}

static ssize_t proc_read_clock(struct file *file,char __user *pszPage,size_t size,loff_t *off)
{
    int len = 0;
    struct timeval xtime;
    do_gettimeofday(&xtime);
    len = sprintf(pszPage,"%ld %ld\n",xtime.tv_sec,xtime.tv_usec);
    printk("clock: read_func()\n");
	if (len <= *off)
	{
		return 0;
	}
	len -= *off;
	*off += len;
    return len;
}

module_init(init_clock);
module_exit(cleanup_clock);

MODULE_DESCRIPTION("clock module for gettimeofday of proc.");
MODULE_LICENSE("GPL");
//EXPORT_NO_SYMBOLS;

